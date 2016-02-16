package control;

import java.io.Serializable;

/**
 * Created by s on 2/15/16.
 */
public class LoginData implements Serializable {
	public String username;
    public String password;

    public LoginData(String name, String pass) {
    	username = name;
    	password = pass;
    }
}
