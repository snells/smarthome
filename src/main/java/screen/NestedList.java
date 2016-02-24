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
    private DoubleClick but = null;
    private int margin;
    private String style;
    //private Resource downIcon = new ThemeResource("icons/down.png");

    private void init() {
    }
    public NestedList() {
        setSpacing(false);
        list = new ArrayList<>();
        style = "";
        setSizeUndefined();
        margin = 0;
        buttons = new ArrayList<>();

    }
    public NestedList(int margin) {
        setSpacing(false);
        list = new ArrayList<>();
        style = "margin-left" + margin;
        setSizeUndefined();
        buttons = new ArrayList<>();
        this.margin = margin;
    }

    public void add(Button b) {
        b.setStyleName("list-but");
        buttons.add(b);
        this.addComponent(b);
    }
    public NestedList nest(String name) {
        DoubleClick b = new DoubleClick(name + FontAwesome.ARROW_DOWN.getHtml());
        but = b;
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

    public DoubleClick getButton(){
        return but;
    }

    public void clear() {
        this.removeAllComponents();
        list = new ArrayList<>();
        buttons = new ArrayList<>();

    }
}
