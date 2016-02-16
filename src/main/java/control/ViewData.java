package control;

import house.SmartObject;

import java.io.Serializable;
import java.util.ArrayList;
import house.House;

public class ViewData implements Serializable {
    public String name;
    public ArrayList<House> houses;
    
    public ViewData(String name, ArrayList<House> houses) {
    	this.name = name;
    	this.houses = houses;
    }
    
    // styles
    //
}
