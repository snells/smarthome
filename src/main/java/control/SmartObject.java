package control;

import control.SmartData;
import sh.Globals;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SmartObject {

	private SmartData data;

	public SmartObject(SmartData d) {
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
		for(Attribute b : data.atrs)
			if(b.name.equals(atr))
				return b.state;
		return "";
	}


	public Attribute getAtr(String name) {
		for(Attribute a : data.atrs)
			if(a.name.equals(name))
				return a;
		return null;
	}

	public void atrB1(String atr) {
		Attribute a = getAtr(atr);
		if(a == null)
			return;
		a.b1fn.fn();
	}

	public void atrB2(String atr) {
		Attribute a = getAtr(atr);
		if(a == null)
			return;
		a.b2fn.fn();
	}

	public void setType(int t) {
		data.type = t;
	}

	public void addAtr(Attribute atr) {
		data.atrs.add(atr);

	}
	public void setName(String n) {
		data.name = n;
	}

	public ArrayList<String> attributeNames() {
		ArrayList<String> names = new ArrayList<>();
		for(int n = 0; n < data.atrs.size(); n++)
			names.add(data.atrs.get(n).name);
		return names;
	}

	public boolean atrHasButton(String atr) {
		Attribute b = getAtr(atr);
		if(b == null)
			return false;
		return b.hasButtons;
	}



}
