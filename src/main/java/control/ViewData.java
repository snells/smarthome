package control;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ViewData implements Serializable {
    public static final long serialVersionUID = 1L;
    public String name;
    //public ArrayList<SmartData> objects;
    public String house;
    public ArrayList<CategoryData> categories;


    public ViewData(String name, String house) {
        this.name = name;
        this.house = house;
        //objects = new ArrayList<>();
        categories = new ArrayList<>();
    }

  /*
    public ViewData(String name, String house) {
    	this.name = name;
        this.house = house;
        //this.objects = objects;
        categories = new ArrayList<>();
    }
*/

    @Override
    public boolean equals(Object o) {
        return name.equals(((ViewData)o).name);

    }

    // styles
    //
}
