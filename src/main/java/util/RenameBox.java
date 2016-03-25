package util;

import com.vaadin.ui.*;

public class RenameBox extends Window {

    public interface Fn {
        public void fn(String name);
    }

    public RenameBox(Button w) {
        super("Rename");
        center();
        VerticalLayout vl = new VerticalLayout();
        TextField tf = new TextField();
        tf.addStyleName("margin-top30");
        setSizeUndefined();
        vl.setSizeUndefined();
        vl.addComponent(tf);
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSizeFull();
        Button b = new Button("Ok");
        Button b2 = new Button("Cancel");
        b.addClickListener(e -> {
            if(tf.getValue().length() == 0) {
                this.close();
                return;
            }
            w.setCaption(tf.getValue());
            this.close();
        });

        b2.addClickListener(e -> {
            this.close();
        });
        hl.addComponents(b, b2);
        hl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        vl.addComponent(hl);
        setContent(vl);
    }


    public RenameBox(Button w, Fn fn) {
        super("Rename");
        center();
        VerticalLayout vl = new VerticalLayout();
        TextField tf = new TextField();
        tf.addStyleName("margin-top30");
        setSizeUndefined();
        vl.setSizeUndefined();
        vl.addComponent(tf);
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSizeFull();
        Button b = new Button("Ok");
        Button b2 = new Button("Cancel");
        b.addClickListener(e -> {
            if(tf.getValue().length() == 0) {
                this.close();
                return;
            }
            //w.setCaption(tf.getValue());
            fn.fn(tf.getValue());
            this.close();
        });

        b2.addClickListener(e -> {
            this.close();
        });
        hl.addComponents(b, b2);
        hl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        vl.addComponent(hl);
        setContent(vl);
    }
}
