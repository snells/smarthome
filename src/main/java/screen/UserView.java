package screen;

import Util.DoubleClick;
import Util.NestedList;
import Util.RenameBox;
import com.vaadin.ui.VerticalLayout;
import control.*;
import sh.Globals;

import java.util.ArrayList;
import java.util.HashMap;

public class UserView extends VerticalLayout {

    private NestedList list;
    private HashMap<DoubleClick, Integer> ids;
    private ArrayList<NestedList> lists;
    private View view = null;
    public UserView() {
        list = new NestedList();
        init(Globals.control.getView("koti", "admin"));
        this.addComponent(list);
        }

    public void init(View data) {
        view = data;
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
        //addSensors(all, view.getObjects());

        for(Category d : data.getCategories()) {
            if (d.getName().length() > 0) {
                NestedList tmp = list.nest(d.getName());
                DoubleClick b = tmp.getButton();
                b.dclick((e, c) -> {
                    if(c >= 2)
                        Globals.ui.addWindow(new RenameBox(b, name -> {
                            if(Globals.control.viewNameFree(name)) {
                                data.setName(name);
                                Globals.control.updateView(d.getName(), data.getData());
                            }
                        }));
                    else
                        ;
                });
                lists.add(tmp);

            }
        }
    }

    private void addSensors(NestedList l, ArrayList<SmartObject> objects) {
        for(SmartObject d : objects) {
            DoubleClick b = new DoubleClick(d.getName());
            b.dclick((e, c) -> {
               if(c >= 2) {
                   Globals.ui.addWindow(new RenameBox(e, name -> {
                       if(Globals.control.viewNameFree(name))
                           d.setName(name);
                   }));
               }
                else {
                   ;
               }
            });
            ids.put(b, d.getId());
            l.add(b);
        }

    }
}
