package screen;

import com.vaadin.ui.HorizontalLayout;
import util.DoubleClick;
import util.NestedList;
import util.RenameBox;
import com.vaadin.ui.VerticalLayout;
import control.*;
import sh.Globals;


public class UserView extends HorizontalLayout {

    ViewCtrl viewCtrl;
    private View view = null;
    User user;
    ObjectCtrl objectView = null;
    public UserView(User user) {

        this.user = user;
        //this.setSizeFull();
        objectView = new ObjectCtrl(null);
        view = Globals.control.getView(Globals.control.getHomeName(), user.getView());
        init();
        this.addComponents(viewCtrl, objectView);
        viewCtrl.addStyleName("margin-rl30");
    }

    public void init() {
        viewCtrl = new ViewCtrl(view, !(user.getRight() == User.RIGHT.USER),
                t -> {},
                id -> { objectView.update(Globals.control.getObject(id)); });

    }

    public void tip() {}

}