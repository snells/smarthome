package control;

import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable {
    public static final long serialVersionUID = 1L;
    public String name;
    public String password;
    public String view;
    public User.RIGHT right;


    public UserData(String name, String pass, User.RIGHT right, String view) {
        this.name = name;
        password = pass;
        this.right = right;
        this.view = view;
    }
}



