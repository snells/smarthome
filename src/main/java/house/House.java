package house;

import control.HouseData;
import control.SmartData;
import house.Room;

import java.io.Serializable;
import java.util.ArrayList;


public class House {
    private HouseData data;

    public House(HouseData data) {
        this.data = data;

    }

    public HouseData getData() {
        return data;
    }
    
    
    public ArrayList<SmartObject> getObjects() {
        ArrayList<SmartObject> objects = new ArrayList<>();
        for(SmartData d : data.objects)
            objects.add(new SmartObject(d));
        return objects;
    }
}
