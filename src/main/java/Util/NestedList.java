package Util;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;

public class NestedList extends VerticalLayout {
    private ArrayList<NestedList> list;
    private ArrayList<Button> buttons;
    private DoubleClick but = null;
    private int margin;
    private String style;
    private InputWindow.Fn fn = t -> {
        System.out.println("default fn");
    };

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
        but = new DoubleClick();

    }

    public NestedList(int margin, DoubleClick b) {
        setSpacing(false);
        list = new ArrayList<>();
        style = "margin-left" + margin;
        setSizeUndefined();
        buttons = new ArrayList<>();
        this.margin = margin;
        but = b;
    }

    public void add(Button b) {
        b.setStyleName("list-but");
        buttons.add(b);
        this.addComponent(b);
    }

    public NestedList nest(String name, InputWindow.Fn fn) {
        DoubleClick b = new DoubleClick(name + FontAwesome.ARROW_DOWN.getHtml());
        but = b;
        b.setHtmlContentAllowed(true);
        NestedList l = new NestedList(margin + 15, b);
        l.setVisible(false);
        b.addStyleName("list-but");
        //b.setIcon(downIcon);
        b.addClickListener(e -> {
            if(l.isVisible()) {
                l.setVisible(false);
                b.setCaption(Util.firstWord(b.getCaption()) + FontAwesome.ARROW_DOWN.getHtml());
            }
            else {
                String x = Util.firstWord(b.getCaption());
                fn.fn(x);
                l.setVisible(true);
                b.setCaption(x + FontAwesome.ARROW_UP.getHtml());
            }
        });
        l.setStyleName("margin-left" + (margin + 30));
        this.addComponent(b);
        this.addComponent(l);
        return l;
    }

     public NestedList nest(String name, boolean open, InputWindow.Fn fn) {
        DoubleClick b = new DoubleClick(name + FontAwesome.ARROW_DOWN.getHtml());
        but = b;
        b.setHtmlContentAllowed(true);
        NestedList l = new NestedList(margin + 15, b);
        l.setVisible(open);
        b.addStyleName("list-but");
        //b.setIcon(downIcon);
        b.addClickListener(e -> {
            if(l.isVisible()) {
                l.setVisible(false);
                b.setCaption(Util.firstWord(b.getCaption()) + FontAwesome.ARROW_DOWN.getHtml());
            }
            else {
                String x = Util.firstWord(b.getCaption());
                fn.fn(x);
                l.setVisible(true);
                b.setCaption(x + FontAwesome.ARROW_UP.getHtml());
            }
        });
        l.setStyleName("margin-left" + (margin + 30));
        this.addComponent(b);
        this.addComponent(l);
        return l;
    }



    public NestedList nest(String name) {
        DoubleClick b = new DoubleClick(name + FontAwesome.ARROW_DOWN.getHtml());
        but = b;
        b.setHtmlContentAllowed(true);
        NestedList l = new NestedList(margin + 15, b);
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
    public NestedList nest(String name, boolean show) {
        DoubleClick b = new DoubleClick(name + FontAwesome.ARROW_DOWN.getHtml());
        but = b;
        b.setHtmlContentAllowed(true);
        NestedList l = new NestedList(margin + 15, b);
        l.setVisible(show);
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
