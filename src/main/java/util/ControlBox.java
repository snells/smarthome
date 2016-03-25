package util;



import com.vaadin.ui.*;

public class ControlBox extends Window {

    public ControlBox(InputWindow.Fn rename, YesNoBox.NoParam delete) {
        super("");
        center();
        setSizeUndefined();
        VerticalLayout vl = new VerticalLayout();
        TextField tf = new TextField();
        tf.addStyleName("margin-top30");
        vl.setSizeUndefined();
        vl.addComponent(tf);
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSizeFull();
        Button b = new Button("Rename");
        Button b2 = new Button("Cancel");
        Button b3 = new Button("Delete");
        b.addStyleName("margin30");
        b2.addStyleName("margin30");
        b3.addStyleName("margin30");
        b.addClickListener(e -> {
            if(tf.getValue().length() == 0) {
                close();
                return;
            }
            rename.fn(tf.getValue());
            close();
        });

        b2.addClickListener(e -> {
            this.close();
        });

        b3.addClickListener(e -> {
            YesNoBox box = new YesNoBox("Are you sure you want to delete " + Util.firstWord(b.getCaption()),
                    () -> {
                        delete.fn();
                        close();
                    },
                    () -> {
                    }
            );
            UI.getCurrent().addWindow(box);
        });
        hl.addComponents(b, b3, b2);
        hl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        vl.addComponent(hl);
        setContent(vl);
    }


}
