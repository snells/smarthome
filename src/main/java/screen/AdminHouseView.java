package screen;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import control.HouseData;
import sh.Globals;

import java.util.ArrayList;


public class AdminHouseView extends AbsoluteLayout {

    private VerticalLayout houseBox = new VerticalLayout();
    private UserView userView = new UserView();
    private ArrayList<HouseData> houses;
    private HouseData currentHouse;
    private Resource homeIcon = new ThemeResource("icons/home.png");
    public AdminHouseView() {
        houses = Globals.control.getHouses();

        houses.forEach(h -> houseBox.addComponent(genBut(h.name)));
        houseBox.setSizeFull();
        houseBox.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        this.addComponent(houseBox, "left: 25%; right: 25%; top: 15px");
    }


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


    private void houseSelect() {
        houseBox.setVisible(true);
        userView.setVisible(false);
    }
    private void viewsShow() {
        houseBox.setVisible(false);
        userView.init(Globals.control.getView(currentHouse.name, "admin"));
        userView.setVisible(true);
    }
    public void show() {
         houseSelect();
    }
}




/*

private VerticalLayout viewsBox = new VerticalLayout();
    private HorizontalLayout allViewsCtrl = new HorizontalLayout();
    private ListSelect viewsList = new ListSelect();
    private UserView userView = new UserView();

    public AdminHouseView() {



        viewsBox.addComponents(allViewsCtrl, viewsList);
        viewsBox.setExpandRatio(allViewsCtrl, 0.1f);
        viewsBox.setExpandRatio(viewsList, 0.9f);

        this.addComponent(viewsBox);
        setExpandRatio(viewsBox, 0.2f);
    }

    private void deleteView() {

    }
    private void newView() {

    }
 */