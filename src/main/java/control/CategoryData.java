package control;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by s on 2/23/16.
 */
public class CategoryData implements Serializable {
    public String name;
    public ArrayList<SmartData> objects;

    public CategoryData(String name, HouseData house) {
        this.name = name;
        objects = house.objects;
    }

    public CategoryData(String name) {
        this.name = name;
        objects = new ArrayList<>();
    }
}
