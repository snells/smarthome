package screen;

import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import control.CategoryData;
import control.SmartData;
import control.ViewData;
import sh.Globals;

import java.util.ArrayList;

public class UserView extends VerticalLayout {

    private NestedList list;
    public UserView() {
        //list = new NestedList();
        init(Globals.control.getView("koti", "admin"));
        this.addComponent(list);
        }



    public void init(ViewData data) {
        list = new NestedList();
        ArrayList<NestedList> lists = new ArrayList<>();

        NestedList all = list.nest("All");
        for(SmartData d : data.objects) {
            DoubleClick b = new DoubleClick(d.name);
            all.add(b);
        }


        for(CategoryData d : data.categories)
            if(d.name.length() > 0)
                lists.add(list.nest(d.name));

    }
}
