package screen;

import Util.SelectorList;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import control.Category;
import control.CategoryData;
import control.SmartData;
import control.View;

import java.util.ArrayList;


public class GroupBox extends HorizontalLayout {
    private ArrayList<Category> data;
    private View view;
    private ListSelect groupList;
    private SelectorList groupItems;

    public GroupBox(View v) {
        view = v;
        data = v.getCategories();
        groupList = new ListSelect();
        groupItems = new SelectorList("", "");
        clearList();
        groupList.addValueChangeListener(e -> {
            if(e.getProperty().getValue() == null)
                return;

        });
    }


    private void clearItems() {
        groupItems.clear();
    }
    private void clearList() {
        groupList.removeAllItems();
        groupList.addItem("Add new");
        ArrayList<String> names = new ArrayList<>();
        data.forEach(e -> names.add(e.getName()));
        groupList.addItems(names);
    }


}
