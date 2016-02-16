package control;

import control.LoginData;
import control.ViewData;

import java.util.ArrayList;
import java.io.File;
/**
 * Created by s on 2/15/16.
 */
public class FileHandler {

	// helpoin varmaan jos kirjoitat objektina tiedostoon 
	// voit toki tallentaa xml/json/? tiedostona mutta todennäköisesti vaikeampaa 
	
	private File path = new File(System.getProperty("user.home"));
	// private File confFile = new File(path + "/.smarthome-conf");
	// private File loginFile = new File(path + ".loginData");
	// private File viewFile = new File(path + ".viewData");
	
    public static ArrayList<LoginData> loadLogin() {
        return null;
    }
    public static void saveLogin(ArrayList<LoginData> data) {
    	
    	
    }
    
    public static ArrayList<ViewData> loadViews() {
        return null;
    }
    public static void saveViews(ArrayList<ViewData> data) {}


    public static void saveConf(HomeConf conf) {}
    
    public static HomeConf loadConf() {
    	return null;
    	
    }
}

