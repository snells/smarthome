package control;

import control.HouseData;
import control.SmartData;
import control.SmartObject;

import java.util.ArrayList;


public class House {
    private HouseData data;

    public House(HouseData data) {
        this.data = data;

    }

    public HouseData getData() {
        return data;
    }

    public static ArrayList<House> genHouses(ArrayList<HouseData> data) {
        ArrayList<House> h = new ArrayList<>();
        data.forEach(e -> h.add(new House(e)));
        return h;
    }
    
    public ArrayList<SmartObject> getObjects() {
        ArrayList<SmartObject> objects = new ArrayList<>();
        for(SmartData d : data.objects)
            objects.add(new SmartObject(d));
        return objects;
    }
}
