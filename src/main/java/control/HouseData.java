package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class HouseData implements Serializable {
    public static final long serialVersionUID = 1L;

    public String name;
    public ArrayList<String> rooms;
    public ArrayList<SmartData> objects;

    public HouseData(String name, ArrayList<String> rooms, ArrayList<SmartData> objects) {
        this.name = name;
        this.rooms = rooms;
        this.objects = objects;
    }

}
