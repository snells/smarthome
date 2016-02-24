package control;

import house.House;
import screen.Screen;
import sh.Globals;

import java.util.ArrayList;

public class Control {
	private FileHandler fileHandler = new FileHandler();
	private ArrayList<User> users;
	private ArrayList<ViewData> viewsData;
	private ArrayList<View> views;
	private HomeConf conf;
	private ArrayList<House> houses;
	private int lastId = -1;

	public Control() {
	}


	public void init() {
		users = new ArrayList<>();
		ArrayList<UserData> tmpData = fileHandler.loadLogin();
		if(tmpData.size() == 0)
			tmpData = Default.genDefaultUsers();
		for (UserData d : tmpData)
			users.add(new User(d));
		viewsData = fileHandler.loadViews();
		if (viewsData.size() == 0) {
			viewsData = Default.genDefaultViews();
		}
		views = new ArrayList<>();
		for(ViewData d : viewsData)
			views.add(new View(d));

		conf = fileHandler.loadConf();

		for(HouseData d : conf.houses)
			for(SmartData s : d.objects)
				if(s.id > lastId)
					lastId = s.id;
		lastId++;
	}

	public int getUniqueId() {
		int tmp = lastId;
		lastId++;
		return tmp;
	}

	public boolean login(String name, String pass) {
		if(Globals.user != null)
			throw new RuntimeException("Trying to login while user is still logged in");
		for(User u : users) {
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
		for(User u : users)
			ret.add(u.getData());
		return ret;
	}

	public User getUser(String name) {
		for(User u : users)
			if(u.getName().equals(name))
				return u;
		return null;
	}
	public boolean addUser(String name, String pass, User.RIGHT right) {
		for(User u : users)
			if(u.getName().equals(name))
				return false;
		users.add(new User(name, pass, right));
		fileHandler.saveLogin(usersData());
		return true;
	}

	public boolean addUser(String name, String pass, User.RIGHT right, String view) {
		for(User u : users)
			if(u.getName().equals(name))
				return false;
		users.add(new User(name, pass, right, view));
		fileHandler.saveLogin(usersData());
		return true;
	}


	public boolean removeUser(String name) {
		for(User u : users)
			if(u.getName().equals(name)) {
				users.remove(u);
				fileHandler.saveLogin(usersData());
				return true;
			}
		return false;
	}

	public boolean userUpdateName(String name, String newName) {
		User u = getUser(name);
		if(u == null)
			return false;
		u.setName(newName);
		fileHandler.saveLogin(usersData());
		return true;
	}
	public void userUpdatePassword(String name, String newPassword) {
		User u = getUser(name);
		if(u == null)
			return;
		u.setPassword(newPassword);
		fileHandler.saveLogin(usersData());
	}

	public void userUpdateView(String name, String v) {
		User u = getUser(name);
		if(u == null)
			return;
		u.setView(v);
		fileHandler.saveLogin(usersData());
	}

	public void addView(ViewData v) {
		viewsData.add(v);

		fileHandler.saveViews(viewsData);

	}


	private void checkUserViews(String v) {
		boolean change = false;
		for(User u : users)
			if(u.getView().equals(v)) {
				u.setView("default");
				change = true;
			}
		if(Globals.user != null || Globals.user.getView().equals(v))
			Globals.user.setView("default");

		if(change)
			fileHandler.saveLogin(usersData());
	}

	public void removeView(ViewData v) {
		for(ViewData d : viewsData)
			if(d.name.equals(v.name)) {
				viewsData.remove(v);
				fileHandler.saveViews(viewsData);
				checkUserViews(v.name);
				return;
			}
	}

	public ViewData getView(String house, String name) {
		String h = null;
		for(HouseData d : conf.houses)
			if(d.name.equals(house)) {
				h = d.name;
				break;
			}
		if(h == null)
			return null;
		for(ViewData v : viewsData)
			if(v.house.equals(h) && v.name.equals(name))
				return v;
		return null;
	}

	public boolean hasPassword(String name) {
		return (getUser(name).getPassword().length() != 0);
	}

	public ArrayList<ViewData> getViews() {
		return viewsData;
	}
	public ArrayList<ViewData> getUserViews() {
		return viewsData;
	}

	public ArrayList<String> getViewsNames() {
		ArrayList<String> names = new ArrayList<>();
		for(ViewData v : viewsData)
			names.add(v.name);
		return names;
	}

	public static String[] rooms(ArrayList<SmartData> sd) {
		String[] s = new String[sd.size()];
		int n = 0;
		for(SmartData d : sd)
			s[n++] = d.name;
		return s;
	}

	public void updateView(String name, ViewData d) {
		for(int n = 0; n < viewsData.size(); n++) {
			ViewData tmp = viewsData.get(n);
			if(tmp.name.equals(name))
				viewsData.set(n, d);
			View v = views.get(n);
			if(v.getName().equals(name))
				views.set(n, new View(d));
		}
		fileHandler.saveViews(viewsData);
	}

	public boolean viewNameFree(String name) {
		for(ViewData d : viewsData)
			if(d.name.equals(name))
				return false;
		return true;
	}
	public ArrayList<HouseData> getHouses() {
		return conf.houses;
	}

	public ArrayList<SmartData> getAllObjects(String house) {
		ArrayList<SmartData> objects = new ArrayList<>();
		HouseData k = null;
		for(HouseData d : conf.houses)
			if(d.name.equals(house)) {
				k = d;
				break;
			}
		if(k == null)
			return objects;
		objects.addAll(k.objects);
		return objects;
	}

	public ArrayList<String> getHouseNames() {
		ArrayList<String> names = new ArrayList<>();
		for(HouseData d : conf.houses)
			names.add(d.name);
		return names;
	}

}
