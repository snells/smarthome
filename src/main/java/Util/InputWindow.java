package Util;

import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

import java.io.InputStream;


public class InputWindow extends Window {


    public interface Fn {
        public void fn(String name);
    }


    public InputWindow(String text, Fn fn) {
        super(text);
        center();
        VerticalLayout vl = new VerticalLayout();
        TextField tf = new TextField();
        tf.addStyleName("margin-top30");
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
