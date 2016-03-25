package control;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class User {
    public enum RIGHT { ADMIN, USER, PASSWORD }
    private UserData data;


    public User(String name, String pass, RIGHT right) {

        data = new UserData(name, pass, right, "default", new HashMap<>());
    }
    public User(UserData data) {
        this.data = data;
    }


    public User(String name, String pass, RIGHT right, String view) {

        data = new UserData(name, pass, right, view , new HashMap<>());
    }


    public static ArrayList<User> genUsers(ArrayList<UserData> data) {
        ArrayList<User> s = new ArrayList<>();
        data.forEach(e -> s.add(new User(e)));
        return s;
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


    public User.RIGHT getRight() {
        return data.right;
    }
    public boolean isAdmin() { return data.right == RIGHT.ADMIN ? true : false; }

    public String getView() {
        return data.view;
    }
    public void noTips() { data.showTips = false; }
    public boolean tips() { return data.showTips; }

}
