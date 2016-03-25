package util;

import com.vaadin.ui.*;
import sh.Globals;


public class TipsBox extends Window {

    public TipsBox(String text) {
        super("");
        center();
        Label ta = new Label(text);
        ta.addStyleName("margin30");
        setWidth("320px");
        setHeight("240px");
        this.setSizeUndefined();
        HorizontalLayout hl = new HorizontalLayout();
        Button b = new Button("");
        b.addClickListener(e -> {
            Globals.user.noTips();
            close();
        });
        b.setWidth("5px");
        b.setHeight("8px");
        b.addStyleName("margin-rl30");
        Label l = new Label("Never show tips again ever");
        hl.addComponents(b, l);
        hl.setComponentAlignment(b, Alignment.MIDDLE_LEFT);
        hl.setComponentAlignment(l, Alignment.TOP_CENTER);
        VerticalLayout vl = new VerticalLayout();
        vl.setSizeUndefined();
        vl.addComponents(ta, hl);
        vl.setExpandRatio(ta, 0.8f);
        vl.setExpandRatio(hl, 0.2f);
        vl.setComponentAlignment(hl, Alignment.BOTTOM_LEFT);
        vl.setComponentAlignment(ta, Alignment.MIDDLE_CENTER);
        setContent(vl);
    }
}
