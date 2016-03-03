package screen;

import com.vaadin.ui.VerticalLayout;
import control.SmartData;
import control.SmartObject;

public class ObjectCtrl extends VerticalLayout {
    private SmartObject object;

    public ObjectCtrl() {}
    public ObjectCtrl(SmartObject object) {
        this.object = object;
    }

    public void update(SmartObject o) {}

}
