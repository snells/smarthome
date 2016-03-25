package screen;

import com.vaadin.ui.*;
import control.Attribute;
import control.SmartData;
import control.SmartObject;

import java.util.ArrayList;


public class ObjectCtrl extends HorizontalLayout {
    private SmartObject object;
    private VerticalLayout split = new VerticalLayout();
    private VerticalLayout upper = new VerticalLayout();
    private HorizontalLayout ctrlButs;
    private ListSelect list = new ListSelect("Attributes");
    private Button b1;
    private Button b2;
    ArrayList<String> attributes;
    Attribute currentAtr;
    public ObjectCtrl(SmartObject object) {

        this.object = object;
        b1 = new Button();
        b2 = new Button();
        upper = new VerticalLayout();
        ctrlButs = new HorizontalLayout();
        this.setSizeFull();
        list.setHeight("100%");

        list.addValueChangeListener(e -> {
            String atr = (String)e.getProperty().getValue();
            selectAtr(atr);
        });

        ctrlButs.setVisible(false);
        split.addComponents(upper, ctrlButs);
        ctrlButs.addComponents(b1, b2);
        ctrlButs.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        split.addStyleName("margin-rl30");
        this.addComponents(list, split);
    }

    public void update(SmartObject o) {
        if(o == null) {
            empty();
            return;
        }
        object = o;
        attributes = o.attributeNames();
        list.removeAllItems();
        list.addItems(object.attributeNames());

    }

    public void updateState() {
    }

    private void updateUpper() {
        upper.removeAllComponents();
        if(currentAtr == null)
            return;
        Label name = new Label("Sensor " + currentAtr.name);
        Label state = new Label("State " + currentAtr.state);
        upper.addComponents(name, state);
    }

    private void empty() {
        list.removeAllItems();
    }

    private void emptyView() {
        ctrlButs.setVisible(false);
        upper.removeAllComponents();
    }

    private void updateButs() {
        if(currentAtr == null)
            return;
        ctrlButs.removeAllComponents();
        b1 = new Button(currentAtr.b1);
        b2 = new Button(currentAtr.b2);
        b1.addStyleName("margin30");
        b2.addStyleName("margin30");
        b1.addClickListener(e -> {
            if(object == null)
                return;
            if(currentAtr == null)
                return;
            object.atrB1(currentAtr.name);
            updateUpper();
        });
        b2.addClickListener(e -> {
            if(object == null)
                return;
            if(currentAtr == null)
                return;
            object.atrB2(currentAtr.name);
            updateUpper();
        });
        ctrlButs.addComponents(b1, b2);
        if(currentAtr.hasButtons)
            ctrlButs.setVisible(true);
        else
            ctrlButs.setVisible(false);
    }

    private void selectAtr(String name) {
        if(name == null) {
            emptyView();
            return;
        }
        Attribute b = object.getAtr(name);
        if(b == null)
            return;
        currentAtr = b;
        updateUpper();
        if(b.hasButtons)
            updateButs();
        else
            ctrlButs.setVisible(false);


    }

}
