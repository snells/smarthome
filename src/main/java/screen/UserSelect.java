package screen;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import control.User;
import control.UserData;
import sh.Globals;

import java.util.ArrayList;


public class UserSelect extends AbsoluteLayout implements Screen {
    private Resource userIcon;
    private ArrayList<UserData> users;
    private User selected;
    private HorizontalLayout loginBox;
    private PasswordField loginField;
    private Label loginInfo;
    private Button loginBut;
    private VerticalLayout vbox;
    public UserSelect() {
        loginInfo = new Label("");
        loginInfo.addStyleName("error-font");
        loginInfo.addStyleName("margin15");
        loginInfo.addStyleName("margin-top40");
        loginInfo.setVisible(false);
        selected = null;
        loginField = new PasswordField("");
        loginField.setWidth("200px");
        loginField.addStyleName("margin15");
        loginField.addStyleName("margin-bot40");
        loginBut = new Button("login");
        loginBut.addStyleName("margin15");
        loginBut.addClickListener(e -> {
            if(loginField.getValue().equals(selected.getPassword())) {
                loginInfo.removeStyleName("error-font");
                loginInfo.addStyleName("success-font");
                loginInfo.setValue("success");
                loginInfo.setVisible(true);
            }
            else {
                loginInfo.setVisible(true);
                loginInfo.removeStyleName("success-font");
                loginInfo.addStyleName("error-font");
                loginInfo.setValue("Wrong password");
                loginField.setValue("");
            }
        });

        loginBox = new HorizontalLayout();
        loginBox.addStyleName("popup-box");
        loginBox.addComponents(loginField, loginBut, loginInfo);
        loginBox.setComponentAlignment(loginField, Alignment.MIDDLE_LEFT);
        loginBox.setComponentAlignment(loginBut, Alignment.MIDDLE_CENTER);
        loginBox.setComponentAlignment(loginField, Alignment.MIDDLE_RIGHT);
        loginBox.setVisible(false);
        userIcon = new ThemeResource("icons/user.png");
        users = Globals.control.usersData();
        vbox = new VerticalLayout();
        vbox.addStyleName("border-l-r");
        this.addComponent(vbox, "left: 25%; right: 25%; top: 15px");
        this.addComponent(loginBox, "left: 25%; right: 25%; top: 40%");


        this.addLayoutClickListener(e -> {
            if(!loginBox.isVisible())
                return;
            System.out.println(e.getClickedComponent());
            if(e.getClickedComponent() == null || e.getClickedComponent().equals(vbox))
                hidePass();
        });
    }
    @Override
    public void show() {
        users = Globals.control.usersData();
        vbox.removeAllComponents();
        for(UserData d : users) {
            Button b = genBut(d.name);
            vbox.addComponent(b);
            vbox.setComponentAlignment(b, Alignment.TOP_CENTER);
        }

    }

    @Override
    public void hide() {
        this.setVisible(false);
        hidePass();
    }

    @Override
    public void alert(String stuff) {

    }

    private void hidePass() {
        loginBox.setVisible(false);
        loginInfo.setVisible(false);
        loginField.setValue("");
        vbox.removeStyleName("fuzzy");
    }

    private void showPassField() {
        vbox.addStyleName("fuzzy");
        loginBox.setVisible(true);
    }

    private Button genBut(String name) {
        Button b = new Button(name);
        b.addStyleName("user-select");
        b.setIcon(userIcon);
        b.addClickListener(e -> {
            if(loginBox.isVisible()) {
                hidePass();
                return;
            }
            String t = e.getButton().getCaption();
            if(Globals.control.hasPassword(t)) {
                selected = Globals.control.getUser(t);
                showPassField();
            }
            else {
                User s = Globals.control.getUser(t);
                if(s == null) {
                    return;
                }
                Globals.user = s;
                Globals.root.changeScreen(Globals.user);
            }
        });
        return b;
    }
}
