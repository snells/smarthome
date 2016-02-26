package Util;

import com.vaadin.ui.Button;

/**
 * Created by s on 2/24/16.
 */
public class IDButton extends Button {

    private int id;
    public IDButton(int id) {
        super();
        this.id = id;
    }
    public IDButton(String name, int id) {
        super(name);
        this.id = id;
    }


    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }


}
