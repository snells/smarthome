package control;

import java.io.Serializable;


public class SmartData implements Serializable {

    public static final long serialVersionUID = 1L;
    public String name;
    public String room;
    public int id;

    public SmartData(String name, String room, int id) {
        this.name = name;
        this.room = room;
        this.id = id;
    }
}
