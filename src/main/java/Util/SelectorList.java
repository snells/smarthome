package Util;

import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class SelectorList extends VerticalLayout {
    private HorizontalLayout hl;
    private VerticalLayout left;
    private VerticalLayout right;
    private Label titleLeft;
    private Label titleRight;
    private ListSelect listLeft;
    private ListSelect listRight;
    private ArrayList<String> valsLeft;
    private ArrayList<String> valsRight;
    private int lastId = 0;
    public SelectorList(String l, String r) {
        hl = new HorizontalLayout();
        this.setSizeUndefined();
        hl.setSizeFull();
        hl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        left = new VerticalLayout();
        right = new VerticalLayout();
        left.addStyleName("margin-30");
        right.addStyleName("margin-30");
        left.setSizeUndefined();
        right.setSizeUndefined();
        titleLeft = new Label(l);
        titleRight = new Label(r);
        listLeft = new ListSelect();
        listRight = new ListSelect();
        listLeft.setSizeFull();
        listRight.setSizeFull();
        left.addComponents(titleLeft, listLeft);
        right.addComponents(titleRight, listRight);

        valsLeft = new ArrayList<>();
        valsRight = new ArrayList<>();

        hl.addComponents(left, right);
        this.addComponents(hl);
    }

    private void addAllVal(String v) {
        if(valsLeft.contains(v))
            return;
        valsLeft.add(v);
        listLeft.addItem(v);
        //listLeft.setItemCaption(lastId, v);

        }

    private void addVal(String v) {
        if(valsRight.contains(v) || !valsLeft.contains(v))
            return;
        valsRight.add(v);
        listRight.addItem(v);

    }
    private void removeVal(String v) {
        if(!valsRight.contains(v))
            return;
        valsRight.remove(v);
        listRight.removeAllItems();
        listRight.addItems(valsRight);
    }

    public void addAll(ArrayList<String> vals) {
        vals.forEach(e -> addAllVal(e));
    }

    public void select(ArrayList<String> vals) {
        vals.forEach(e -> addVal(e));
    }

    public ArrayList<String> getAll() {
        ArrayList<String> l = new ArrayList<>();
        valsLeft.forEach(e -> l.add(e));
        return l;
    }
    public ArrayList<String> getSelected() {
        ArrayList<String> l = new ArrayList<>();
        valsRight.forEach(e -> l.add(e));
        return l;
    }

    public interface Fn {
        public void fn(String v);
    }

    public void addValSelect(Fn fn) {
        listLeft.addValueChangeListener(e -> {
            String t = (String)e.getProperty().getValue();
            if(t == null)
                return;
            addVal(t);
            fn.fn(t);
        });
    }

    public void addValDelete(Fn fn) {
        listRight.addValueChangeListener(e -> {
            String t = ((String) e.getProperty().getValue());
            if(t == null)
                return;
            fn.fn(t);
            removeVal(t);

        });
    }
}