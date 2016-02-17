package house;

import control.HouseData;
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
    	return null;
    }
    


}
