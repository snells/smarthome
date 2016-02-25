package screen;

import Util.SelectorList;
import com.vaadin.ui.*;
import control.SmartData;
import control.User;
import control.UserData;
import control.ViewData;
import jdk.nashorn.internal.objects.Global;
import sh.Globals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class AdminViewsView extends HorizontalLayout {


    private ViewData currentView = null;
    private ListSelect viewList = new ListSelect();
    private ArrayList<ViewData> views;
    private VerticalLayout ctrlView = new VerticalLayout();
    private Label info = new Label();


    public AdminViewsView() {
        info.addStyleName("margin-bot30");
        viewList.setWidth("100%");
        viewList.setHeight("100%");
        ctrlView.setWidth("100%");
        ctrlView.setHeight("100%");
        viewList.addValueChangeListener(e -> {
            String t = (String)e.getProperty().getValue();
            if(t == null)
                return;
            if(t.equals("Add new")) {
                ctrlAddNew();
                return;
            }

            ViewData d = getView(t);
            if(d == null)
                return;

            updateCtrl(d);
        });
        views = Globals.control.getViews();

        addComponents(viewList, ctrlView);
        setExpandRatio(viewList, 0.2f);
        setExpandRatio(ctrlView, 0.8f);
    }


    private ViewData getView(String name) {
        for(ViewData d : views)
            if(d.name.equals(name))
                return d;
        return null;
    }


    private HorizontalLayout makeBox(String field, String name) {
        HorizontalLayout hl = new HorizontalLayout();
        Label l  = new Label(field);
        l.addStyleName("margin-rl30");
        hl.addComponent(l);
        TextField tf = new TextField();
        tf.addStyleName("margin-rl30");
        hl.addComponent(tf);
        Button b = new Button(name);
        b.addStyleName("margin-rl30");
        b.addClickListener(e -> {
            handle(field, tf.getValue());
            tf.setValue("");
        });
        hl.addComponent(b);
        hl.setComponentAlignment(l, Alignment.MIDDLE_LEFT);
        hl.setComponentAlignment(tf, Alignment.MIDDLE_CENTER);
        hl.setComponentAlignment(b, Alignment.MIDDLE_RIGHT);
        return hl;
    }

    private void handle(String cmd, String text) {
        /*
        if(text.length() == 0) {
            info.addStyleName("error-font");
            info.setValue("Field can't be empty you fucking retard");
            return;
        }
        String t = currentUser.name;
        if(cmd.equals("username")) {
            Globals.control.userUpdateName(t, text);
            currentUser.name = text;
            System.out.println("username " + t  + " new name " + text);
            info.removeStyleName("error-font");
            info.addStyleName("success-font");
            info.setValue("Update successful");
            updateList();
        }
        else if(cmd.equals("password")) {
            currentUser.password = text;
            Globals.control.userUpdatePassword(t, text);
            info.removeStyleName("error-font");
            info.addStyleName("success-font");
            info.setValue("Update successful");
        }
        */
    }

    private SelectorList objectSelector(ArrayList<SmartData> objects) {
        SelectorList sl = new SelectorList("All items", "Views items");
        ArrayList<SmartData> all = Globals.control.getAllObjects("koti");
        ArrayList<String> names = new ArrayList<>();
        all.forEach(e -> names.add(e.name + " id " + e.id));
        sl.addAll(names);
        ArrayList<String> tmp = new ArrayList<>();
        objects.forEach(e -> tmp.add(e.name + " id " + e.id));
        sl.select(tmp);
        sl.addValSelect(s -> {
            System.out.println(s.split(" ")[0]);
        });
        sl.addValDelete(s -> {
            String[] tt = s.split(" ");
            System.out.println(tt[0]);
        });
        return sl;
    }

    private void updateCtrl(ViewData d) {
        info.setValue("");
        info.removeStyleName("error-font");
        info.removeStyleName("success-font");
        if(null == d) {
            ctrlView.removeAllComponents();
            return;
        }
        if(currentView != null && d.name.equals(currentView.name))
            return;
        currentView = d;
        ctrlView.removeAllComponents();
        ctrlView.addComponent(info);
        HorizontalLayout un = makeBox("name", "update");
        un.addStyleName("margin-bot30");
        ctrlView.addComponent(un);

        if(!(d.name.equals("admin") && !d.name.equals("default"))) {
            Button del = new Button("Delete");
            del.addClickListener(e -> alertDel(d.name));
            del.addStyleName("margin-top40");
            del.addStyleName("margin-rl30");
            ctrlView.addComponent(del);
        }
        SelectorList sl = objectSelector(d.objects);
        ctrlView.addComponent(sl);
        ctrlView.setExpandRatio(info, 0.1f);
        ctrlView.setExpandRatio(un, 0.3f);
        ctrlView.setExpandRatio(sl, 0.6f);
    }

    private void ctrlAddNew() {
        ctrlView.removeAllComponents();
    }

    private void alertDel(String name) {
        Window w = new Window("Delete view");
        HorizontalLayout hl = new HorizontalLayout();
        //hl.addStyleName("popup-box");
        Label l = new Label("Delete view " + name + "?");
        l.addStyleName("margin-rl30");
        hl.addComponent(l);
        Button b = new Button("Yes");
        b.addStyleName("margin-rl30");
        Button b2 = new Button("YES");
        b2.addStyleName("margin-rl30");
        b2.addStyleName("margin-r80");

        b.addClickListener(e -> {
            Globals.control.removeView(name);
            views = Globals.control.getViews();
            updateList();
            ctrlView.removeAllComponents();
            w.close();
        });
        b2.addClickListener(e -> {
            w.close();
        });

        hl.addComponents(b, b2);

        w.setSizeUndefined();
        w.center();
        w.setContent(hl);
        Globals.ui.addWindow(w);
    }


    private void updateList() {
        viewList.removeAllItems();
        ArrayList<String> names = new ArrayList<>();
        names.add("Add new");
        for(ViewData d : views)
            if(d.name.length() > 0 || !d.name.equals("admin"))
                names.add(d.name);
        viewList.addItems(names);
    }


    public void show() {
        views = Globals.control.getViews();
        currentView = null;
        updateList();
        ctrlView.removeAllComponents();
    }

}
