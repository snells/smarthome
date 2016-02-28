package control;

import control.SmartData;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SmartObject {

	private SmartData data;
	private HashMap<String,String> atr;

	public SmartObject(SmartData d) {
		atr = new HashMap<>();
		data = d;

	}

	public SmartData getData() {
		return data;
	}

	public static ArrayList<SmartObject> genObjects(ArrayList<SmartData> data) {
		ArrayList<SmartObject> objects = new ArrayList<>();
		data.forEach(e -> objects.add(new SmartObject(e)));
		return objects;
	}

	public static ArrayList<String> getNames(ArrayList<SmartObject> objects) {
		ArrayList<String> names= new ArrayList<>();
		objects.forEach(e -> names.add(e.getName()));
		return names;
	}

	public static ArrayList<Integer> getIds(ArrayList<SmartObject> objects) {
		ArrayList<Integer> names= new ArrayList<>();
		objects.forEach(e -> names.add(e.getId()));
		return names;
	}

	public static ArrayList<String> getNamesIds(ArrayList<SmartObject> objects) {
		ArrayList<String> names= new ArrayList<>();
		objects.forEach(e -> names.add(e.getName() + " id " + e.getId()));
		return names;
	}
	public static ArrayList<String> getRawNames(ArrayList<SmartData> data) {
		ArrayList<String> names= new ArrayList<>();
		data.forEach(e -> names.add(e.name));
		return names;
	}

	public static boolean hasObject(ArrayList<SmartObject> objects, int id) {
		for(SmartObject o : objects)
			if(o.getId() == id)
				return true;
		return false;
	}

	public String getName() {
		return data.name;
	}

	public int getId() {
		return data.id;
	}

	public String atrState(String atr) {
		return null;
	}

	public void setName(String n) {
		data.name = n;
	}
	public String[] attributes() {
		return (String[])atr.keySet().toArray();
	}




}
