package house;

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



	public String atrState(String atr) {
		return null;
	}

	public String[] attributes() {
		return (String[])atr.keySet().toArray();
	}




}
