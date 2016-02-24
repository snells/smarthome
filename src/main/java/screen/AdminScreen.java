package screen;

import com.vaadin.ui.*;
import sh.Globals;

/**
 * Created by s on 2/15/16.
 */
public class AdminScreen extends AbsoluteLayout implements Screen {

    private HorizontalLayout navbar = new HorizontalLayout();
    private AbsoluteLayout mainView = new AbsoluteLayout();
    private Button userBut = new Button("Users");
    private Button housesBut = new Button("Houses");
    private Button viewBut = new Button("Views");
    private Button logoutBut = new Button("Logout");
    private AdminUserView usersView = new AdminUserView();
    private AdminHouseView housesView = new AdminHouseView();
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
        viewBut.setHeight("100%");
        viewBut.addClickListener(e -> switchView(2));
        housesBut.addStyleName("margin-rl30");
        housesBut.addStyleName("border-node");
        housesBut.setHeight("100%");
        housesBut.addClickListener(e -> switchView(1));
        //logoutBut.addStyleName("margin-rl30");
        logoutBut.addStyleName("border-node");
        logoutBut.setHeight("100%");
        logoutBut.addClickListener(e -> {
            Globals.user = null;
            Globals.root.changeScreen(TYPE.USER_SELECT);
        });
        HorizontalLayout navh = new HorizontalLayout();
        navh.setSizeUndefined();
        navh.setHeight("100%");
        navh.addComponents(userBut, housesBut, viewBut);
        navbar.setWidth("100%");

        navbar.addComponents(navh, logoutBut);
        //navbar.setComponentAlignment(userBut, Alignment.MIDDLE_RIGHT);
        //navbar.setComponentAlignment(housesBut, Alignment.MIDDLE_LEFT);
        //navbar.setExpandRatio(navh, 0.8f);
        //navbar.setExpandRatio(logoutBut, 0.2f);
        navbar.setComponentAlignment(navh, Alignment.MIDDLE_LEFT);
        navbar.setComponentAlignment(logoutBut, Alignment.MIDDLE_RIGHT);
        navbar.setHeight("10%");

        mainView.setSizeFull();
        mainView.addComponents(usersView, housesView, viewsView);
        usersView.setSizeFull();
        usersView.setVisible(false);
        housesView.setSizeFull();
        viewsView.setSizeFull();
        viewsView.setVisible(false);

        this.addComponent(navbar, "top: 0px;");
        this.addComponent(mainView, "top: 10%;");

    }

    private void switchView(int n) {
        if(n == 0) {
            usersView.show();
            usersView.setVisible(true);
            housesView.setVisible(false);
            viewsView.setVisible(false);
        }
        else if(n == 1) {
            housesView.show();
            usersView.setVisible(false);
            housesView.setVisible(true);
            viewsView.setVisible(false);

        }
        else {
            viewsView.show();
            usersView.setVisible(false);
            housesView.setVisible(false);
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
