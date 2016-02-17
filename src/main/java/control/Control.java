package control;

import elemental.html.File;
import house.House;
import house.SmartObject;
import screen.Root;
import screen.Screen;
import sh.Globals;

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
		Globals.root.changeScreen(Screen.TYPE.LOGIN);
	}


	public boolean addUser(String name, String pass, User.RIGHT right) {
		for(User u : users)
			if(u.getName().equals(name))
				return false;
		users.add(new User(name, pass, right));
		return true;
	}

	public boolean removeUser(String name) {
		for(User u : users)
			if(u.getName().equals(name)) {
				users.remove(u);
				return true;
			}
		return false;
	}

//	public void addToConf(String house, String room, int obj) {}

//	public void removeFromConf(String house, String room, int obj) {}


	public void addView(ViewData v) {}
	public void removeView(ViewData v) {}
	public void removeView(String name) {}



}
