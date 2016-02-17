package control;

import sh.Globals;

import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElementDecl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Default {


    public static ArrayList<ViewData> genDefaultViews() {
        ArrayList<ViewData> ret = new ArrayList<>();
        ret.add(new ViewData("admin", "1234", genDefaultHouses()));
        ret.add(new ViewData("default", "", genDefaultHouses()));
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

    public static ArrayList<HouseData> genDefaultHouses() {
        ArrayList<HouseData> ret = new ArrayList<>();
        ret.add(new HouseData("koti",
                new ArrayList<String>(Arrays.asList("huone1", "huone2")),
                genDefaultObjects()));
        return ret;
    }

}
