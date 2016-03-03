package screen;

import com.vaadin.ui.HorizontalLayout;
import util.DoubleClick;
import util.NestedList;
import util.RenameBox;
import com.vaadin.ui.VerticalLayout;
import control.*;
import sh.Globals;

import java.util.ArrayList;
import java.util.HashMap;

public class UserView extends HorizontalLayout {

    ViewCtrl viewCtrl;
    private View view = null;
    User user;
    ObjectCtrl objectView = null;
    public UserView(User user) {

        this.user = user;
        objectView = new ObjectCtrl();
        view = Globals.control.getView(Globals.control.getHomeName(), user.getView());
        init();
        this.addComponent(viewCtrl);
    }

    public void init() {
        objectView.removeAllComponents();
        viewCtrl = new ViewCtrl(view, !(user.getRight() == User.RIGHT.USER),
                t -> {},
                id -> { objectView.update(Globals.control.getObject(id)); });

    }


}