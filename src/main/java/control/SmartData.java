package control;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class SmartData implements Serializable {

    public static final long serialVersionUID = 1L;
    public String name;
    public String room;

    public int id;
    public int type;
    public ArrayList<Attribute> atrs;

    public SmartData(String name, String room, int id) {
        this.name = name;
        this.room = room;
        this.id = id;
        type = 0;
        atrs = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        return id == ((SmartData)o).id;
    }
}
