package control;


import org.w3c.dom.Attr;
import util.YesNoBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Attribute implements Serializable {

    public interface AtrFn extends Serializable {
        public void fn();
    }

    public interface AtrWrapper extends Serializable {
        public void wrapper(Attribute b);
    }
    public String name;
    public String state;
    public String b1;
    public String b2;
    public AtrFn b1fn;
    public AtrFn b2fn;
    public boolean hasButtons;
    public Attribute(String name, String state) {
        this.name = name;
        this.state = state;
        b1 = "";
        b2 = "";
        b1fn = () -> {};
        b2fn = () -> {};
        hasButtons = false;

    }
    public Attribute(String name, String state, String b1, String b2, AtrWrapper f1, AtrWrapper f2) {
        this.name =name;
        this.state = state;
        this.b1 = b1;
        this.b2 = b2;
        b1fn = () -> {f1.wrapper(this); };
        b2fn = () -> {f2.wrapper(this); };
        hasButtons = true;
    }

}
