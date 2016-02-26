package control;


import java.io.*;
import java.util.ArrayList;

public class FileHandler {

	
	private  File path = new File(System.getProperty("user.home"));
	private  File confFile = new File(path + "/.smarthome-conf");
	private  File loginFile = new File(path + "/.loginData");
	private  File viewFile = new File(path + "/.viewData");
	
    public  ArrayList<UserData> loadLogin() {
        ArrayList<UserData> data;
        data = load(loginFile);
        if(data == null)
            return new ArrayList<>();
        return data;
    }

    public  void saveLogin(ArrayList<UserData> data) {
        write(data, loginFile);
    }
    
    public  ArrayList<ViewData> loadViews() {
        ArrayList<ViewData> views;
        views = load(viewFile);
        if(views == null)
            return new ArrayList<>();
        return views;
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
        } catch (Exception e) { System.out.println("ERROR loading file" + f); }
        return ret;
    }
        
    private  <T> void write(T data, File f) {
        try {
            FileOutputStream fo = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fo);
            os.writeObject(data);
            os.close();
            fo.close();
        } catch (Exception e) { System.out.println("ERROR saving file" + f); }
        }
}

