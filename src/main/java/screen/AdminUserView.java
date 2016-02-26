package screen;

import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import control.User;
import control.UserData;
import control.View;
import control.ViewData;
import sh.Globals;

import java.util.ArrayList;


public class AdminUserView extends HorizontalLayout {

    ArrayList<UserData> users;
    private UserData currentUser = null;
    private ListSelect userList = new ListSelect();
    private VerticalLayout ctrlView = new VerticalLayout();
    private Label info = new Label();


    public AdminUserView() {
        info.addStyleName("margin-bot30");
        userList.setWidth("100%");
        userList.setHeight("100%");
        ctrlView.setWidth("100%");
        ctrlView.setHeight("100%");
        userList.addValueChangeListener(e -> {
            String t = (String)e.getProperty().getValue();
            if(t == null)
                return;
            if(t.equals("Add new")) {
                ctrlAddNew();
                return;
            }

            UserData d = getUser(t);
            if(d == null)
                return;
            updateCtrl(d);
        });
        users = Globals.control.usersData();

        addComponents(userList, ctrlView);
        setExpandRatio(userList, 0.2f);
        setExpandRatio(ctrlView, 0.8f);
    }


    private UserData getUser(String name) {
        for(UserData d : users)
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
    }
    private void updateCtrl(UserData d) {
        info.setValue("");
        info.removeStyleName("error-font");
        info.removeStyleName("success-font");
        if(null == d) {
            ctrlView.removeAllComponents();
            return;
        }
        if(currentUser != null && d.name.equals(currentUser.name))
            return;
        currentUser = d;
        ctrlView.removeAllComponents();
        HorizontalLayout un = makeBox("username", "update");
        un.addStyleName("margin-bot30");
        HorizontalLayout pn =  makeBox("password", "update");

        Button del = new Button("Delete");
        del.addClickListener(e -> alertDel(d.name));
        del.addStyleName("margin-top40");
        del.addStyleName("margin-rl30");
        VerticalLayout logBox = new VerticalLayout();
        logBox.addComponents(un, pn);
        if(d.right != User.RIGHT.ADMIN)
            logBox.addComponent(del);

        NativeSelect ns = new NativeSelect();
        ArrayList<View> views = Globals.control.getUserViews();
        //ArrayList<String> names = new ArrayList<>();
        int n = 0;
        int current = 0;
        for(View v : views) {
            if(v.getName().equals("admin"))
                continue;
            ns.addItem(n);
            if(v.getName().equals(currentUser.view))
                current = n;
            ns.setItemCaption(n++, v.getName());
        }
        HorizontalLayout viewBox = new HorizontalLayout();
        Label vl = new Label("Select view");
        vl.addStyleName("margin-rl30");
        viewBox.addComponent(vl);
        //ns.addItems(names);
        ns.select(current);
        ns.addValueChangeListener(e -> {
            String v = ns.getItemCaption(e.getProperty().getValue());
            if(v == null) {
                v = ns.getItemCaption(0);
                ns.select(0);
            }
            System.out.println("Selected " + v);
            currentUser.view = v;
            Globals.control.userUpdateView(currentUser.name, v);
            users = Globals.control.usersData();
        });
        viewBox.addComponent(ns);
        ctrlView.addComponents(info, logBox);
        if(!(currentUser.right == User.RIGHT.ADMIN)) {
            ctrlView.addComponent(viewBox);
            ctrlView.setComponentAlignment(viewBox, Alignment.MIDDLE_LEFT);
            ctrlView.setExpandRatio(info, 0.2f);
            ctrlView.setExpandRatio(logBox, 0.3f);
            ctrlView.setExpandRatio(viewBox, 0.5f);
        }
        else {
            ctrlView.setExpandRatio(info, 0.2f);
            ctrlView.setExpandRatio(logBox, 0.8f);

        }

    }

    private void ctrlAddNew() {
        ctrlView.removeAllComponents();

        VerticalLayout logBox = new VerticalLayout();

        HorizontalLayout hl = new HorizontalLayout();
        Label l = new Label("Username");
        l.addStyleName("margin-bot30");
        l.addStyleName("margin-rl30");
        hl.addComponent(l);
        TextField tf = new TextField();
        tf.addStyleName("margin-bot30");
        tf.addStyleName("margin-rl30");
        hl.addComponent(tf);
        hl.setComponentAlignment(l, Alignment.MIDDLE_LEFT);
        hl.setComponentAlignment(tf, Alignment.MIDDLE_CENTER);

        HorizontalLayout hl2 = new HorizontalLayout();
        Label lp = new Label("Password");
        lp.addStyleName("margin-rl30");
        hl2.addComponent(lp);
        TextField tf2 = new TextField();
        tf2.addStyleName("margin-rl30");
        hl2.addComponent(tf2);
        hl2.setComponentAlignment(lp, Alignment.MIDDLE_LEFT);
        hl2.setComponentAlignment(tf2, Alignment.MIDDLE_CENTER);


        NativeSelect ns = new NativeSelect();

        Button b = new Button("Add");
        b.addClickListener(e -> {

            if (tf.getValue().length() == 0 || Globals.control.getUser(tf.getValue()) != null) {
                info.removeStyleName("success-font");
                info.addStyleName("error-font");
                info.setValue("Name can't be empty and it must be unique");
            }

            else {
                Globals.control.addUser(tf.getValue(), tf2.getValue(),
                        tf2.getValue().length() == 0 ? User.RIGHT.USER : User.RIGHT.PASSWORD,
                        ns.getCaption());
                System.out.println("caption " + ns.getCaption());
                users = Globals.control.usersData();
                updateList();
                info.removeStyleName("error-font");
                info.addStyleName("success-font");
                info.setValue("Add success");
            }
            tf.setValue("");
            tf2.setValue("");
        });
        logBox.addComponents(hl, hl2, b);


        ArrayList<View> views = Globals.control.getUserViews();
        int n = 0;
        int current = 0;
        for (View v : views) {
            if (v.getName().equals("admin"))
                continue;
            ns.addItem(n);
            if (v.getName().equals("default"))
                current = n;
            ns.setItemCaption(n++, v.getName());
        }
        HorizontalLayout viewBox = new HorizontalLayout();
        Label vl = new Label("Select view");
        vl.addStyleName("margin-rl30");
        viewBox.addComponents(vl, ns);
        //ns.addItems(names);
        ns.select(current);
        ns.addValueChangeListener(e -> {
            String v = ns.getItemCaption(e.getProperty().getValue());
            if (v == null) {
                v = ns.getItemCaption(0);
                ns.select(0);
            }
        });

            ctrlView.addComponents(info, logBox, viewBox);
            ctrlView.setExpandRatio(info, 0.2f);
            ctrlView.setExpandRatio(logBox, 0.3f);
            ctrlView.setExpandRatio(viewBox, 0.5f);
            viewBox.setDefaultComponentAlignment(Alignment.TOP_LEFT);

    }

    private void alertDel(String name) {
        Window w = new Window();
        HorizontalLayout hl = new HorizontalLayout();
        //hl.addStyleName("popup-box");
        Label l = new Label("Kill " + name + "?");
        l.addStyleName("margin-rl30");
        hl.addComponent(l);
        Button b = new Button("Yes");
        b.addStyleName("margin-rl30");
        Button b2 = new Button("YES");
        b2.addStyleName("margin-rl30");
        b2.addStyleName("margin-r80");

        b.addClickListener(e -> {
            Globals.control.removeUser(name);
            users = Globals.control.usersData();
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
        userList.removeAllItems();
        ArrayList<String> names = new ArrayList<>();
        names.add("Add new");
        for(UserData d : users)
            if(d.name.length() > 0)
                names.add(d.name);
        userList.addItems(names);
    }


    public void show() {
        users = Globals.control.usersData();
        userList.removeAllItems();
        ArrayList<String> names = new ArrayList<>();
        names.add("Add new");
        for(UserData d : users)
            if(d.name.length() > 0)
                names.add(d.name);
        userList.addItems(names);
        ctrlView.removeAllComponents();
    }
}
