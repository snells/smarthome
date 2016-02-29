package screen;

import util.SelectorList;
import com.vaadin.ui.*;
import control.*;
import control.View;
import sh.Globals;

import java.util.ArrayList;


public class AdminViewsView extends HorizontalLayout {


    private View currentView = null;
    private ListSelect viewList = new ListSelect();
    private ArrayList<View> views;
    private HorizontalLayout ctrlView = new HorizontalLayout();
    private House house;
    private Label info = new Label();
    private SelectorList sl = null;



    public AdminViewsView() {
        info.addStyleName("margin-bot30");
        viewList.setWidth("100%");
        viewList.setHeight("100%");
        ctrlView.setWidth("100%");
        ctrlView.setHeight("100%");
        viewList.addValueChangeListener(e -> {
            if(sl != null)
                sl.setVisible(false);
            String t = (String)e.getProperty().getValue();
            if(t == null)
                return;
            if(t.equals("Add new")) {
                ctrlAddNew();
                return;
            }

            View d = getView(t);
            if(d == null)
                return;

            updateCtrl(d);
        });
        house = Globals.control.getHome();
        views = Globals.control.getViews();
        ctrlView.setSizeUndefined();
        Panel p = new Panel(ctrlView);
        p.setSizeFull();
        addComponents(viewList, p); //, ctrlView);
        setExpandRatio(viewList, 0.2f);
        setExpandRatio(p, 0.8f);
        //setExpandRatio(ctrlView, 0.8f);
    }


    private View getView(String name) {
        for(View d : views)
            if(d.getName().equals(name))
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
        if(text.length() == 0) {
            info.addStyleName("error-font");
            info.setValue("Field can't be empty you fucking retard");
            return;
        }

        if(cmd.equals("name")) {
            ArrayList<String> names = Globals.control.getViewsNames();
            if(names.contains(text)) {
                info.addStyleName("error-font");
                info.setValue("Name already used");
                return;
            }
            Globals.control.addView(new ViewData(text, "koti"));
            updateList();

        }
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

    private void updateSelector(String name) {
        sl.clearSelected();
        sl.setVisible(true);
        sl.setTitles("All objects", name);
        Category c = currentView.getCategory(name);
        System.out.println("selector " + name);
        if(c == null)
            return;
        sl.select(SmartObject.getNamesIds(c.getObjects()));

    }
    private void updateCtrl(View d) {
        house = Globals.control.getHouse(d.getHouse());
        info.setValue("");
        info.removeStyleName("error-font");
        info.removeStyleName("success-font");
        if(null == d) {
            ctrlView.removeAllComponents();
            return;
        }
        /*
        if(currentView != null && d.getName().equals(currentView.getName()))
            return;
            */
        sl = new SelectorList("All objects", "");//d.getName());
        sl.addAll(SmartObject.getNamesIds(Globals.control.getHouseObjects(d.getHouse())));
        //sl.select(SmartObject.getNamesIds(d.getObjects()));
        sl.setVisible(false);
        currentView = d;
        ctrlView.removeAllComponents();
        ctrlView.addComponent(info);
        ViewCtrl ctrl = new ViewCtrl(currentView, true, t -> updateSelector(t));
        ctrl.addStyleName("margin30");
        sl.addValSelect(t -> {
            String ids = t.split(" ")[2];
            int id = Integer.parseInt(ids);
            System.out.println("id " + id);
            System.out.println("ctrlview selected " + ViewCtrl.selected);
            Category x = currentView.getCategory(ViewCtrl.selected);
            SmartObject o = Globals.control.getObject(id);
            x.addObject(o);
            currentView.save();

        });
        sl.addValDelete(t -> {
            String ids = t.split(" ")[2];
            int id = Integer.parseInt(ids);
            System.out.println("id " + id);
            System.out.println("ctrlview selected " + ViewCtrl.selected);
            Category x = currentView.getCategory(ViewCtrl.selected);
            SmartObject o = Globals.control.getObject(id);
            x.removeObject(o);
            currentView.save();
        });
        ctrlView.addComponent(ctrl);
        ctrlView.addComponent(sl);
        ctrlView.setComponentAlignment(ctrl, Alignment.MIDDLE_LEFT);
        ctrlView.setComponentAlignment(sl, Alignment.MIDDLE_RIGHT);
        /*
        HorizontalLayout un = makeBox("name", "update");
        un.addStyleName("margin-bot30");
        ctrlView.addComponent(un);

        if(!d.getName().equals("default")) {
            Button del = new Button("Delete");
            del.addClickListener(e -> alertDel(d.getName()));
            del.addStyleName("margin-top40");
            del.addStyleName("margin-rl30");
            ctrlView.addComponent(del);
        }

        HorizontalLayout hl = new HorizontalLayout();
        ListSelect ls = new ListSelect(d.getHouse() + " sensors");
        ls.addItems(SmartObject.getNamesIds(Globals.control.getHouseObjects(d.getHouse())));
        ls.addValueChangeListener(e -> {
            if(e.getProperty().getValue() == null)
                return;

        });
        NestedList nl = new NestedList();
*/

    }

    private void ctrlAddNew() {
        ctrlView.removeAllComponents();
        HorizontalLayout box = makeBox("name", "add");
        ctrlView.addComponents(info, box);
        ctrlView.setExpandRatio(info, 0.1f);
        ctrlView.setExpandRatio(box, 0.9f);
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
        for(View d : views)
            if(d.getName().length() > 0 && !d.getName().equals("admin"))
                names.add(d.getName());
        viewList.addItems(names);
    }


    public void show() {
        views = Globals.control.getViews();
        currentView = null;
        updateList();
        ctrlView.removeAllComponents();
    }
    public void update() {
        views = Globals.control.getViews();
        updateList();
        currentView = Globals.control.getView(currentView.getHouse(), currentView.getName());
        if(currentView == null)
            return;
        System.out.println("Updating ctrl");
        updateCtrl(currentView);
    }

}







/*


        info.setValue("");
        info.removeStyleName("error-font");
        info.removeStyleName("success-font");
        if(null == d) {
            ctrlView.removeAllComponents();
            return;
        }
        if(currentView != null && d.getName().equals(currentView.getName()))
            return;
        currentView = d;
        ctrlView.removeAllComponents();
        ctrlView.addComponent(info);
        HorizontalLayout un = makeBox("name", "update");
        un.addStyleName("margin-bot30");
        ctrlView.addComponent(un);

        if(!d.getName().equals("default")) {
            Button del = new Button("Delete");
            del.addClickListener(e -> alertDel(d.getName()));
            del.addStyleName("margin-top40");
            del.addStyleName("margin-rl30");
            ctrlView.addComponent(del);
        }
        groupBox = new GroupBox(currentView);
        groupBox.addStyleName("margin-rl30");
        SelectorList sl = objectSelector(d.getData().objects);
        sl.addStyleName("margin30");
        ctrlView.addComponents(sl, groupBox);

        /*
        ctrlView.setExpandRatio(info, 0.1f);
        ctrlView.setExpandRatio(un, 0.3f);
        ctrlView.setExpandRatio(sl, 0.3f);
        */