package control;

import sh.Globals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Default {


    public static ArrayList<ViewData> genDefaultViews() {
        ArrayList<ViewData> ret = new ArrayList<>();
        ret.add(new ViewData("admin", "koti"));//, genDefaultObjects()));
        ret.add(new ViewData("default", "koti"));//, genDefaultObjects()));
        return ret;
    }

    public static ArrayList<SmartData> genDefaultObjects() {
        ArrayList<SmartData> ret = new ArrayList<>();
        ret.add(new SmartData("sensori1", "huone1", Globals.control.getUniqueId()));
        ret.add(new SmartData("sensori2", "huone1", Globals.control.getUniqueId()));
        ret.add(new SmartData("sensori3", "huone2", Globals.control.getUniqueId()));
        ret.add(new SmartData("sensori4", "huone2", Globals.control.getUniqueId()));
        return ret;
    }

    public static ArrayList<UserData> genDefaultUsers() {
        ArrayList<UserData> ret = new ArrayList<>();
        ret.add(
                new UserData("admin", "1234", User.RIGHT.ADMIN, "admin", new HashMap<String, String>()));
        ret.add(
                new UserData("test", "", User.RIGHT.USER, "default", new HashMap<String, String>()));
        return ret;
    }

    public static House genDefaultHouse() {
        House h;
        h = new House(new HouseData("koti",
                new ArrayList<String>(Arrays.asList("huone1", "huone2")),
                genDefaultObjects()));
        return h;
    }


	public static ArrayList<HouseData> genDefaultConf() {
        ArrayList<HouseData> houses = new ArrayList<>();
        houses.add(Default.genDefaultHouse().getData());
        return houses;
	}

    private static SmartData genDoor() {
        SmartObject o = new SmartObject(new SmartData("Ovi", "huone1", Globals.control.getUniqueId()));
        return o.getData();
    }

    private static SmartData genLamp() {
        SmartObject o = new SmartObject(new SmartData("Lamppu", "huone1", Globals.control.getUniqueId()));
        o.addAtr(new Attribute("päällä", "ei", "On", "Off",
                () -> {  },
                () -> { }));
        return o.getData();
    }
    private static SmartData genTable() {
        SmartObject o = new SmartObject(new SmartData("Pöytä", "huone1", Globals.control.getUniqueId()));
        return o.getData();
    }


}
