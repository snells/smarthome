package screen;

import com.vaadin.ui.VerticalLayout;
import control.SmartObject;

public class ObjectCtrl extends VerticalLayout {
    private SmartObject object;

    public ObjectCtrl(SmartObject object) {
        this.object = object;
    }


}
