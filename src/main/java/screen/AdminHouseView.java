package screen;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import control.HouseData;
import sh.Globals;

import java.util.ArrayList;


public class AdminHouseView extends AbsoluteLayout {

    private VerticalLayout houseBox = new VerticalLayout();
    private UserView userView;
    private ArrayList<HouseData> houses;
    private HouseData currentHouse;
    private Panel housePanel;
    private Resource homeIcon = new ThemeResource("icons/home.png");
    public AdminHouseView() {
        houses = Globals.control.getHouses();
        userView = new UserView(Globals.control.getUser("admin"));
        houses.forEach(h -> houseBox.addComponent(genBut(h.name)));
        houseBox.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        housePanel = new Panel();
        housePanel.setSizeFull();
        housePanel.setContent(houseBox);
        houseBox.setWidth("100%");
        this.addComponent(housePanel, "left: 25%; right: 25%; top: 15px");
        this.addComponent(userView);
        userView.setVisible(false);
        //this.addComponent(houseBox, "left: 25%; right: 25%; top: 15px");
        //this.addComponent(userView, "left: 0%; right: 75%; top: 0px");
        //userView.setVisible(false);
    }


    public void tip() {}
    private HouseData getHouse(String name) {
        for(HouseData d : houses)
            if(d.name.equals(name))
                return d;
        throw new RuntimeException("ERROR trying to get house that does not exists");
    }

    private Button genBut(String name) {
        Button b = new Button(name);
        b.addStyleName("user-select");
        b.setIcon(homeIcon);
        b.addClickListener(e -> {
            currentHouse = getHouse(name);
            viewsShow();
            });
        return b;
    }


    private void houseShow() {
        housePanel.setVisible(true);
        userView.setVisible(false);
    }
    private void viewsShow() {
        housePanel.setVisible(false);
        userView.setVisible(true);
        //houseBox.setVisible(false);
        //userView.init(Globals.control.getView(currentHouse.name, "admin"));
        //userView.setVisible(true);
    }
    public void show() {
         houseShow();
    }
}


