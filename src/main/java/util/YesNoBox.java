package util;

import com.vaadin.ui.*;


public class YesNoBox extends Window {

    public interface NoParam {
        public void fn();
    }

    public YesNoBox(String text, NoParam yes, NoParam no) {
        super("");
        center();

        Label l = new Label(text);
        Button y = new Button("yes");
        Button n = new Button("no");
        y.setStyleName("margin30");
        n.setStyleName("margin30");
        y.addClickListener(e -> {
            yes.fn();
            close();
        });
        n.addClickListener(e -> {
            no.fn();
            close();
        });
        VerticalLayout vl = new VerticalLayout();
        vl.setSizeUndefined();
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSizeUndefined();
        hl.addComponents(y, n);
        vl.addComponents(l, hl);
        setContent(vl);
    }
}
