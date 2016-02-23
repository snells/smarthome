package screen;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;

public class NestedList extends VerticalLayout {
    private ArrayList<NestedList> list;
    private ArrayList<Button> buttons;
    private int margin;
    private String style;
    private Resource downIcon = new ThemeResource("icons/down.png");

    private void init() {
    }
    public NestedList() {
        setSpacing(false);
        list = new ArrayList<>();
        style = "";
        setSizeUndefined();
        margin = 0;
        buttons = new ArrayList<>();
        Button dummy = new Button();
        dummy.setStyleName("margin-left" + margin);
        dummy.setStyleName("list-but");
        this.addComponent(dummy);
    }
    public NestedList(int margin) {
        setSpacing(false);
        list = new ArrayList<>();
        style = "margin-left" + margin;
        setSizeUndefined();
        buttons = new ArrayList<>();
        this.margin = margin;
        Button dummy = new Button();
        dummy.setStyleName("margin-left" + margin);
        dummy.setStyleName("list-but");
        this.addComponent(dummy);
    }

    public void add(Button b) {
        b.setStyleName("list-but");
        buttons.add(b);
        this.addComponent(b);
    }
    public NestedList nest(String name) {
        Button b = new Button(name + FontAwesome.ARROW_DOWN.getHtml());
        b.setHtmlContentAllowed(true);
        NestedList l = new NestedList(margin + 15);
        l.setVisible(false);
        b.addStyleName("list-but");
        //b.setIcon(downIcon);
        b.addClickListener(e -> {
            if(l.isVisible()) {
                l.setVisible(false);
                b.setCaption(b.getCaption().split(" ")[0] + FontAwesome.ARROW_DOWN.getHtml());
            }
            else {
                l.setVisible(true);
                b.setCaption(b.getCaption().split(" ")[0] + FontAwesome.ARROW_UP.getHtml());
            }
        });
        l.setStyleName("margin-left" + (margin + 30));
        this.addComponent(b);
        this.addComponent(l);
        return l;
    }
}
