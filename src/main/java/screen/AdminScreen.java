package screen;

import com.sun.javafx.geom.AreaOp;
import com.vaadin.ui.*;
import sh.Globals;

/**
 * Created by s on 2/15/16.
 */
public class AdminScreen extends AbsoluteLayout implements Screen {

    private HorizontalLayout navbar = new HorizontalLayout();
    private AbsoluteLayout mainView = new AbsoluteLayout();
    private Button userBut = new Button("Users");
    private Button viewBut = new Button("Views");
    private Button logoutBut = new Button("Logout");
    private AdminUserView usersView = new AdminUserView();
    private AdminViewsView viewsView = new AdminViewsView();

    public AdminScreen() {
        //navbar.addStyleName("bg-black");
        navbar.addStyleName("border-bot");
        navbar.setSpacing(false);
        userBut.addStyleName("margin-rl30");
        userBut.setHeight("100%");
        userBut.addClickListener(e -> switchView(0));
        userBut.addStyleName("border-node");
        viewBut.addStyleName("margin-rl30");
        viewBut.addStyleName("border-node");
        viewBut.setHeight("100%");
        viewBut.addClickListener(e -> switchView(1));
        logoutBut.addStyleName("margin-rl30");
        logoutBut.addStyleName("border-node");
        logoutBut.setHeight("100%");
        logoutBut.addClickListener(e -> {
            Globals.user = null;
            Globals.root.changeScreen(TYPE.USER_SELECT);
        });
        navbar.setWidth("100%");
        navbar.addComponents(userBut, viewBut, logoutBut);
        navbar.setComponentAlignment(userBut, Alignment.MIDDLE_RIGHT);
        navbar.setComponentAlignment(viewBut, Alignment.MIDDLE_LEFT);
        navbar.setComponentAlignment(logoutBut, Alignment.MIDDLE_RIGHT);
        navbar.setHeight("10%");

        mainView.setSizeFull();
        mainView.addComponent(usersView);
        mainView.addComponent(viewsView);
        usersView.setSizeFull();
        viewsView.setSizeFull();


        this.addComponent(navbar, "top: 0px;");
        this.addComponent(mainView, "top: 10%;");

    }

    private void switchView(int n) {
        if(n == 0) {
            usersView.setVisible(true);
            usersView.show();
            viewsView.setVisible(false);
        }
        else {
            usersView.setVisible(false);
            viewsView.setVisible(true);
        }
    }
    @Override
    public void show() {
        this.setVisible(true);
    }

    @Override
    public void hide() {
        this.setVisible(false);
    }

    @Override
    public void alert(String stuff) {

    }
}
