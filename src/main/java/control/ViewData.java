package control;

import house.SmartObject;

import java.io.Serializable;
import java.util.ArrayList;
import house.House;

public class ViewData implements Serializable {
    public static final long serialVersionUID = 1L;
    public String name;
    public String password;
    public ArrayList<HouseData> houses;
    
    public ViewData(String name, String pass, ArrayList<House> houses) {
    	this.name = name;
        password = pass;
        ArrayList<HouseData> data = new ArrayList<>();
        for(House h : houses)
            data.add(h.getData());
    	this.houses = data;
    }
    
    // styles
    //
}
