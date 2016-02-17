package control;

import elemental.html.File;
import house.House;
import house.SmartObject;
import screen.Root;
import screen.Screen;
import sh.Globals;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

public class Control {
	private FileHandler fileHandler = new FileHandler();
	private ArrayList<User> users;
	private ArrayList<ViewData> views;
	private HomeConf conf;
	private ArrayList<House> houses;

	public Control() {
		users = new ArrayList<>();
		for(UserData d : fileHandler.loadLogin())
			users.add(new User(d));
		views = fileHandler.loadViews();
		conf = fileHandler.loadConf();
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

	private ArrayList<UserData> usersData() {
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

	public boolean removeUser(String name) {
		for(User u : users)
			if(u.getName().equals(name)) {
				users.remove(u);
				fileHandler.saveLogin(usersData());
				return true;
			}
		return false;
	}

	public void userUpdateName(String name, String newName) {
		User u = getUser(name);
		if(u == null)
			return;
		u.setName(newName);
		fileHandler.saveLogin(usersData());
	}
	public void userUpdatePassword(String name, String newPassword) {
		User u = getUser(name);
		if(u == null)
			return;
		u.setPassword(newPassword);
		fileHandler.saveLogin(usersData());
	}


	public void addView(ViewData v) {
		views.add(v);
		fileHandler.saveViews(views);

	}
	public void removeView(ViewData v) {
		views.remove(v);
		fileHandler.saveViews(views);
	}
	public void removeView(String name) {
		ViewData v = null;
		for(ViewData t : views)
			if(t.name.equals(name)) {
				v = t;
				break;
			}
		if(v != null)
			removeView(v);
	}



}
