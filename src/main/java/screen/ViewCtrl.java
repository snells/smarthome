package screen;

import Util.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import control.*;
import jdk.nashorn.internal.runtime.ECMAException;
import sh.Globals;

import java.util.ArrayList;

public class ViewCtrl extends NestedList {
    private View view;
    private NestedList groups;
    private boolean right;
    public static String selected;
    public static ArrayList<String> open;
    InputWindow.Fn fn;

    public ViewCtrl(View v, boolean access, InputWindow.Fn fn) {
        super();
        if (selected == null) {
            selected = "";
            open = new ArrayList<>();
        }
        right = access;
        view = v;
        this.fn = fn;
        update();
    }

    public void addObject(int id) {

    }

    private void addViewFn(DoubleClick but) {
        if(right) {
            but.dclick((b, c) -> {
                if(c >= 2)
                    UI.getCurrent().addWindow(new ControlBox(name -> {
                        view.rename(name);
                            },
                            () -> {
                                if(view.getName().equals("default"))
                                    Alert.alert("Cannot delete default view");
                                else {
                                    Globals.control.removeView(view.getData());
                                    Globals.root.update();
                                }
                            }));
                            /*
                    UI.getCurrent().addWindow(new RenameBox(b, name -> {
                        view.setName(name);
                        view.save();
                        */

                else
                    ;
            });
        }
    }

    private void updateSelected(String t) {
        ViewCtrl.selected = t;
    }
    private void addGroupFn(DoubleClick but, String group) {
        if(right) {
            but.dclick((b, c) -> {
                if (c >= 2)
                    UI.getCurrent().addWindow(new ControlBox(name -> {
                        Category x = view.getCategory(group);
                        if (x == null)
                            return;
                        x.setname(name);
                        view.updateCategory(x);
                    },
                            () -> {
                                view.removeCategory(group);
                            }));
                else {
                    open.add(group);
                    updateSelected(group);
                   // fn.fn(group);
                }
            });
        }
    }


    public void update() {
        this.removeAllComponents();
        groups = nest(view.getName(), true);
         Button b = new Button("new group");
            b.addClickListener(e -> {
                Globals.ui.addWindow(new InputWindow("New group", text -> {
                    view.addCategory(text);
                }));
            });
            groups.add(b);
        addViewFn(getButton());
        for(Category c : view.getCategories()) {
            NestedList l = groups.nest(c.getName(), open.contains(c.getName()), fn);
            addGroupFn(l.getButton(), c.getName());
            for (SmartObject o : c.getObjects())
                l.add(new Button(o.getName()));
        }
        if(selected.length() > 0)
            fn.fn(selected);
    }
}
