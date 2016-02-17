package house;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by s on 2/15/16.
 */
public class Room extends Attribute {
	private House house;
	private String name;
	private ArrayList<SmartObject> objects;
}
