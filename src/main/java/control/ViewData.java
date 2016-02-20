package control;

import house.SmartObject;

import java.io.Serializable;
import java.util.ArrayList;
import house.House;

public class ViewData implements Serializable {
    public static final long serialVersionUID = 1L;
    public String name;
    public ArrayList<SmartData> objects;
    public String house;
    
    public ViewData(String name, String house, ArrayList<SmartData> objects) {
    	this.name = name;
        this.house = house;
        this.objects = objects;
    }



    // styles
    //
}
