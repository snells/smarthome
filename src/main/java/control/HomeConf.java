package control;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeConf implements Serializable {
	public static final long serialVersionUID = 1L;
	public ArrayList<HouseData> houses;

	public HomeConf() {
		houses = Default.genDefaultConf();
	}

	public HomeConf(ArrayList<HouseData> data) {
		houses = data;
	}

}
