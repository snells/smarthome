package control;

import screen.Screen;
import sh.Globals;

import java.util.ArrayList;

public class Control {
	private FileHandler fileHandler = new FileHandler();
	private ArrayList<User> users;
	private ArrayList<View> views;
	private ArrayList<House> houses;
	private ArrayList<SmartObject> objects;
	private HomeConf conf;
	private int lastId = -1;

	public Control() {
	}

	public void init() {
		ArrayList<UserData> tmpData = fileHandler.loadLogin();
		if (tmpData.size() == 0)
			tmpData = Default.genDefaultUsers();
		users = User.genUsers(tmpData);

		ArrayList<ViewData> tmpv = fileHandler.loadViews();
		if (tmpv.size() == 0) {
			tmpv = Default.genDefaultViews();
		}
		views = View.genViews(tmpv);
		conf = fileHandler.loadConf();

		houses = House.genHouses(conf.houses);
		objects = new ArrayList<>();
		for (HouseData d : conf.houses)
			for (SmartData s : d.objects) {
				objects.add(new SmartObject(s));
				if (s.id > lastId)
					lastId = s.id;
			}
		lastId++;
	}

	public int getUniqueId() {
		int tmp = lastId;
		lastId++;
		return tmp;
	}

	public boolean login(String name, String pass) {
		if (Globals.user != null)
			throw new RuntimeException("Trying to login while user is still logged in");
		for (User u : users) {
			if (u.getName().equals(name) && u.getPassword().equals(pass)) {
				Globals.user = u;
				Globals.root.changeScreen(u.isAdmin() ? Screen.TYPE.ADMIN : Screen.TYPE.USER);
				return true;
			}
		}
		return false;
	}

	public void logout() {
		Globals.user = null;
		Globals.root.changeScreen(Screen.TYPE.USER_SELECT);
	}

	public ArrayList<UserData> usersData() {
		ArrayList<UserData> ret = new ArrayList<>();
		for (User u : users)
			ret.add(u.getData());
		return ret;
	}

	public User getUser(String name) {
		for (User u : users)
			if (u.getName().equals(name))
				return u;
		return null;
	}

	public boolean addUser(String name, String pass, User.RIGHT right) {
		for (User u : users)
			if (u.getName().equals(name))
				return false;
		users.add(new User(name, pass, right));
		fileHandler.saveLogin(usersData());
		return true;
	}

	public boolean addUser(String name, String pass, User.RIGHT right, String view) {
		for (User u : users)
			if (u.getName().equals(name))
				return false;
		users.add(new User(name, pass, right, view));
		fileHandler.saveLogin(usersData());
		return true;
	}


	public boolean removeUser(String name) {
		for (User u : users)
			if (u.getName().equals(name)) {
				users.remove(u);
				fileHandler.saveLogin(usersData());
				return true;
			}
		return false;
	}

	public House getHome() {
		return getHouse(getHomeName());
	}

	public String getHomeName() {
		return "koti";
	}

	public SmartObject getObject(int id) {
		for(SmartObject o : getHome().getObjects())
			if(o.getId() == id)
				return o;
		return null;
	}

	public ArrayList<SmartObject> getHouseObjects(String house) {
		House h = getHouse(house);
		if(h == null)
			return new ArrayList<>();
		return h.getObjects();
	}
	public boolean userUpdateName(String name, String newName) {
		User u = getUser(name);
		if (u == null)
			return false;
		u.setName(newName);
		fileHandler.saveLogin(usersData());
		return true;
	}

	public void userUpdatePassword(String name, String newPassword) {
		User u = getUser(name);
		if (u == null)
			return;
		u.setPassword(newPassword);
		fileHandler.saveLogin(usersData());
	}

	public void userUpdateView(String name, String v) {
		User u = getUser(name);
		if (u == null)
			return;
		u.setView(v);
		fileHandler.saveLogin(usersData());
	}

	public void addView(ViewData v) {
		for (View x : views)
			if (v.name.equals(x.getName()))
				return;
		views.add(new View(v));
		fileHandler.saveViews(View.getData(views));
	}


	private void checkUserViews(String v) {
		boolean change = false;
		for (User u : users) {
			if (u.getView() == null)
				u.setView("default");
			else if (u.getView().equals(v)) {
				u.setView("default");
				change = true;
			}
		}
		if (Globals.user != null || Globals.user.getView().equals(v))
			Globals.user.setView("default");
		if (change)
			fileHandler.saveLogin(usersData());
	}

	public void removeView(ViewData v) {
		removeView(v.name);
	}

	public void removeView(String name) {
		for (View d : views)
			if (d.getName().equals(name)) {
				views.remove(d);
				fileHandler.saveViews(View.getData(views));
				checkUserViews(name);
				return;
			}
	}


	public View getView(String house, String name) {
		String h = null;
		for (HouseData d : conf.houses)
			if (d.name.equals(house)) {
				h = d.name;
				break;
			}
		if (h == null)
			return null;
		for (View v : views)
			if (v.getHouse().equals(h) && v.getName().equals(name))
				return v;
		return null;
	}

	public boolean hasPassword(String name) {
		return (getUser(name).getPassword().length() != 0);
	}

	public ArrayList<View> getViews() {
		return views;
	}

	public ArrayList<View> getUserViews() {
		return views;
	}

	public ArrayList<View> getHouseViews(String h) {
		ArrayList<View> views = new ArrayList<>();
		for(View x : this.views)
			if(x.getName().equals(h))
				views.add(x);
		return views;
	}


	public ArrayList<String> getViewsNames() {
		ArrayList<String> names = new ArrayList<>();
		for (View v : views)
			names.add(v.getName());
		return names;
	}

	public static String[] rooms(ArrayList<SmartData> sd) {
		String[] s = new String[sd.size()];
		int n = 0;
		for (SmartData d : sd)
			s[n++] = d.name;
		return s;
	}

	public void updateView(String name, ViewData d) {
		for (int n = 0; n < views.size(); n++) {
			View tmp = views.get(n);
			if (tmp.getName().equals(name))
				views.set(n, new View(d));
		}
		fileHandler.saveViews(View.getData(views));
		Globals.root.update();
	}

	public boolean viewNameFree(String name) {
		for (View d : views)
			if (d.getName().equals(name))
				return false;
		return true;
	}

	public ArrayList<HouseData> getHouses() {
		return conf.houses;
	}

	public House getHouse(String name) {
		for (House h : houses)
			if (h.getName().equals(name))
				return h;
		return null;
	}

	public ArrayList<SmartData> getAllObjects(String house) {
		ArrayList<SmartData> objects = new ArrayList<>();
		HouseData k = null;
		for (HouseData d : conf.houses)
			if (d.name.equals(house)) {
				k = d;
				break;
			}
		if (k == null)
			return objects;
		objects.addAll(k.objects);
		return objects;
	}

	public ArrayList<String> getHouseNames() {
		ArrayList<String> names = new ArrayList<>();
		for (HouseData d : conf.houses)
			names.add(d.name);
		return names;
	}

	public boolean checkViews(String house) {
		House h = getHouse(house);
		ArrayList<SmartObject> objects = h.getObjects();
		for(SmartObject o : objects)
			System.out.println("House " + h.getName() +  " object " + o.getName() + " id " + o.getId());
		ArrayList<View> views = getHouseViews(house);
		for(View v : views)
			if(!checkView(v))
				return false;
		return true;

	}

	public boolean checkView(View v) {
		if (v == null)
			return true;
		ArrayList<SmartObject> objects = getHouse(v.getHouse()).getObjects();
		for (SmartObject o : v.getObjects()) {
			if (!SmartObject.hasObject(objects, o.getId())) {
				System.out.println("Object " + o.getName() + " id " + o.getId());
				return false;
			}
		}
		return true;
	}

	public boolean checkView(String name) {
		View v = getView(getHomeName(), name);
		return checkView(v);
	}

	public void viewsUpdate(SmartObject o) {
		for(View v : views)
			for(SmartObject x : v.getObjects())
				if(x.equals(o))
					v.update(o);
		fileHandler.saveViews(View.getData(views));
		Globals.root.update();
	}
	private void confUpdateObject(SmartObject o) {
		conf.update(o);
		saveConf();
		objects = SmartObject.genObjects(conf.getObjects(getHomeName()));
		Globals.root.update();
	}

	private void saveConf() {
		fileHandler.saveConf(conf);
	}


	public void updateObject(SmartObject object) {
		conf.update(object);
		Globals.root.update();
		//objects = conf.getObjects(getHomeName());
	}
}

