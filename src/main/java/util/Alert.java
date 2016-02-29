package util;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class Alert {

    public static void alert(String text) {
        Window w = new Window();
        VerticalLayout vl = new VerticalLayout();
        Label l = new Label(text);
        l.setWidthUndefined();
        vl.addComponent(l);
        w.setContent(vl);
        w.center();
        UI.getCurrent().addWindow(w);
    }
}
