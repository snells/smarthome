package control;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserData implements Serializable {
    public static final long serialVersionUID = 1L;
    public String name;
    public String password;
    public String view;
    public User.RIGHT right;
    public HashMap<String, String> objectAliases;


    public UserData(String name, String pass, User.RIGHT right, String view, HashMap<String, String> aliases) {
        this.name = name;
        password = pass;
        this.right = right;
        this.view = view;
        this.objectAliases = aliases;
    }

    @Override
    public boolean equals(Object o) {
        return ((UserData)o).name.equals(name);
    }
}



