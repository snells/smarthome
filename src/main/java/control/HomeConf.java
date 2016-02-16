package control;

import java.io.Serializable;
import java.util.ArrayList;
import house.House;

public class HomeConf implements Serializable {
	public ArrayList<House> houses;
	
	public HomeConf(ArrayList<House> houses) {
		this.houses = houses;
	}
}
