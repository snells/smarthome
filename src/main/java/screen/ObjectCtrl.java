package screen;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.VerticalLayout;
import control.SmartData;
import control.SmartObject;

import java.util.ArrayList;

public class ObjectCtrl extends VerticalLayout {
    private SmartObject object;
    ArrayList<String> attributes;
    public ObjectCtrl() {
        object = null;
    }
    public ObjectCtrl(SmartObject object) {
        this.object = object;
    }

    public void update(SmartObject o) {
        object = o;
        this.removeAllComponents();
        attributes = o.attributeNames();
        ListSelect ls = new ListSelect("Sensors");
        ls.addItems(attributes);
        ls.addValueChangeListener(e -> {

        });


        this.addComponents(ls);
    }

}
