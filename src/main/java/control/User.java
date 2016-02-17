package control;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class User {
    public enum RIGHT { ADMIN, USER }
    private UserData data;


    public User(String name, String pass, RIGHT right) {

        data = new UserData(name, pass, right, "default", new HashMap<>());
    }
    public User(UserData data) {
        this.data = data;
    }





    public void setView(String view) {
        data.view = view;
    }


    public UserData getData() {
        return data;
    }

    public String getName() { return  data.name; }
    public String getPassword() { return data.password; }
    public void setName(String name) { data.name = name; }
    public void setPassword(String pass) { data.password = pass; }


    public boolean isAdmin() { return data.right == RIGHT.ADMIN ? true : false; }


}
