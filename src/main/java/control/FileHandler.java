package control;


import java.io.*;
import java.util.ArrayList;

/**
 * Created by s on 2/15/16.
 */
public class FileHandler {

	// helpoin varmaan jos kirjoitat objektina tiedostoon 
	// voit toki tallentaa xml/json/? tiedostona mutta todennäköisesti vaikeampaa 
	
	private  File path = new File(System.getProperty("user.home"));
	private  File confFile = new File(path + "/.smarthome-conf");
	private  File loginFile = new File(path + ".loginData");
	private  File viewFile = new File(path + ".viewData");
	
    public  ArrayList<UserData> loadLogin() {
        return null;
    }

    public  void saveLogin(ArrayList<UserData> data) {

    }
    
    public  ArrayList<ViewData> loadViews() {
        return null;
    }
    public  void saveViews(ArrayList<ViewData> data) {}


    public  void saveConf(HomeConf conf) {
        write(conf, confFile);

    }
    
    
    public HomeConf loadConf() {
    	HomeConf hc = load(confFile);
        if(hc == null)
            return new HomeConf();
        return hc;
    }


    private <T> T load(File f) {
        T ret = null;
        try {
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream oi = new ObjectInputStream(fi);
            ret = (T)oi.readObject();
            oi.close();
            fi.close();
        } catch (Exception e) { e.printStackTrace(); }
        return ret;
    }
        
    private  <T> void write(T data, File f) {
        try {
            FileOutputStream fo = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fo);
            os.writeObject(data);
            os.close();
            fo.close();
        } catch (Exception e) { e.printStackTrace(); }
        }
}

