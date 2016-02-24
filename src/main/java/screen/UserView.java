package screen;

import com.vaadin.shared.Connector;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import control.CategoryData;
import control.SmartData;
import control.ViewData;
import sh.Globals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class UserView extends VerticalLayout {

    private NestedList list;
    private HashMap<DoubleClick, Integer> ids;
    private ArrayList<NestedList> lists;
    private ViewData data = null;
    public UserView() {
        list = new NestedList();
        init(Globals.control.getView("koti", "admin"));
        this.addComponent(list);
        }

    public void init(ViewData data) {
        this.data = data;
        ids = new HashMap<>();
        list.clear();
        lists = new ArrayList<>();

        NestedList all = list.nest("All");
        /*
        for(SmartData d : data.objects) {
            DoubleClick b = new DoubleClick(d.name);

            ids.put(b, d.id);
            all.add(b);
        }
*/
        addSensors(all, data.objects);

        for(CategoryData d : data.categories) {
            if (d.name.length() > 0) {
                NestedList tmp = list.nest(d.name);
                DoubleClick b = tmp.getButton();
                b.dclick((e, c) -> {
                    if(c >= 2)
                        Globals.ui.addWindow(new RenameBox(b, name -> {
                            if(Globals.control.viewNameFree(name)) {
                                data.name = name;
                                Globals.control.updateView(d.name, data);
                            }
                        }));
                    else
                        ;
                });
                lists.add(tmp);

            }
        }
    }

    private void addSensors(NestedList l, ArrayList<SmartData> objects) {
        for(SmartData d : objects) {
            DoubleClick b = new DoubleClick(d.name);
            b.dclick((e, c) -> {
               if(c >= 2) {
                   Globals.ui.addWindow(new RenameBox(e, name -> {
                       if(Globals.control.viewNameFree(name))
                           d.name = name;
                   }));
               }
                else {
                   ;
               }
            });
            ids.put(b, d.id);
            l.add(b);
        }

    }
}
