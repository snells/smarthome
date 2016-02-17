package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by s on 2/17/16.
 */
public class HouseData implements Serializable {
    public static final long serialVersionUID = 1L;

    public String name;
    public ArrayList<String> rooms;
    public ArrayList<SmartData> objects;
}
