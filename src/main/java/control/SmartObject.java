package control;

import control.SmartData;

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


	public static ArrayList<SmartObject> genObjects(ArrayList<SmartData> data) {
		ArrayList<SmartObject> objects = new ArrayList<>();
		data.forEach(e -> objects.add(new SmartObject(e)));
		return objects;
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
