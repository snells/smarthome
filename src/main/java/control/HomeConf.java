package control;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeConf implements Serializable {
	public static final long serialVersionUID = 1L;
	public ArrayList<HouseData> houses;

	public HomeConf() {
		houses = Default.genDefaultConf();
	}

	public HomeConf(ArrayList<HouseData> data) {
		houses = data;
	}

	public void update(SmartObject o ) {
		for(HouseData d : houses)
			for(int n = 0; n < d.objects.size(); n++)
				if(d.objects.get(n).equals(o.getData())) {
					d.objects.set(n, o.getData());
							break;
				}

	}
	public ArrayList<SmartData> getObjects(String house) {
		for(HouseData d : houses)
			if(d.name.equals(house))
				return d.objects;
		return new ArrayList<>();
	}
}
