package house;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Attribute implements Serializable {
	public static final long serialVersionUID = 1L;

	protected HashMap<String,String> atr;

	public String atrState(String atr) {
		return null;
	}

	public String[] attributes() {
		return (String[])atr.keySet().toArray();
	}


}

