package control;


import util.Alert;
import sh.Globals;

import java.util.ArrayList;

public class View {
    private ViewData data;

    public View(String name, String house) {
        data = new ViewData(name, house);
    }

    public static ArrayList<View> genViews(ArrayList<ViewData> data) {
        ArrayList<View> views = new ArrayList<>();
        data.forEach(d -> views.add(new View(d)));
        return views;
    }

    public static ArrayList<ViewData> getData(ArrayList<View> views) {
        ArrayList<ViewData> d = new ArrayList<>();
        views.forEach(e -> d.add(e.getData()));
        return d;
    }

    public String getHouse() {
        return data.house;
    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> c = new ArrayList<>();
        data.categories.forEach(e -> c.add(new Category(e)));
        return c;
    }

    public void setName(String name) {
        data.name = name;
    }

    public View(ViewData d) {
        data = d;
    }

    public ViewData getData() {
        return data;
    }

    public void setCategory(String old, Category c) {
        for(int n = 0; n < data.categories.size(); n++)
            if(data.categories.get(n).name.equals(old)) {
                data.categories.set(n, c.getData());
                return;
            }
    }


    public ArrayList<SmartObject> getObjects() {
        ArrayList<SmartObject> objects = new ArrayList<>();
        for(Category c : getCategories())
            for(SmartObject o : c.getObjects())
                if(!objects.contains(o))
                    objects.add(o);

        return objects;
    }

    public void removeCategory(String name) {
        for(CategoryData d : data.categories)
            if(d.name.equals(name)){
                data.categories.remove(d);
                save();
                return;
            }
    }

    public void addCategory(String name) {
        if(getCategory(name) == null) {
            data.categories.add(new CategoryData(name));
            save();
        }
    }

    public String getName() {
        return data.name;
    }





    public Category getCategory(String name) {
        for(CategoryData d : data.categories)
            if(d.name.equals(name))
                return new Category(d);
        return null;
    }

    public void updateCategory(Category x) {
        if(x == null)
            return;
        setCategory(x.getName(), x);
        save();
    }


    public void addCategoryObject(String group, int id) {
        Category c = getCategory(group);
        c.addObject(Globals.control.getObject(id));
        updateCategory(c);
    }

    public void removeGroupObject(String group, int id) {
        Category c = getCategory(group);
        c.removeObject(Globals.control.getObject(id));
        updateCategory(c);
    }

    public void rename(String name) {
        if(name.equals("default")) {
            Alert.alert("Cannot rename default view");
            return;
        }

        if(name.length() > 0 && Globals.control.viewNameFree(name)) {
            data.name = name;
            save();
            return;
        }
        Alert.alert("Name can't be empty and it must be unique");
    }



    private void renameObject(String cat, int id, String name) {
        Category c = getCategory(cat);
        if(c == null)
            return;
        c.renameObject(id, name);
        updateCategory(c);
    }

    private void renameCategory(String old, String name) {
        Category c = getCategory(old);
        if(c == null)
            return;
        Category x = getCategory(name);
        if(x != null)
            return;
        c.setname(name);
        updateCategory(c);
    }



    public void save() {
        Globals.control.updateView(data.name, data);
    }


    public void update(SmartObject o) {
        for(CategoryData c : data.categories)
            if(c.objects.contains(o.getData()))
                categoryUpdate(c ,o.getData());
    }

    private void categoryUpdate(CategoryData c, SmartData d) {
        int n = 0;
        for(; n < c.objects.size(); n++)
            if(c.objects.get(n).equals(d)) {
                c.objects.set(n, d);
                return;
            }
    }
}



    /*
    public ArrayList<SmartObject> getObjects() {
        return SmartObject.genObjects(data.objects);

    }


    public void addObject(SmartObject o) {
        if(o == null)
            return;
        if(hasObject(o))
            return;
        data.objects.add(o.getData());
        save();
    }

    private void removeObject(SmartObject o) {
        if(o == null)
            return;
        data.objects.remove(o.getData());
        save();
    }


    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        data.objects.forEach(e -> names.add(e.name));
        return names;
    }
    public void removeObject(SmartData o) {
        SmartData d = getObject(o.id);
        if(d == null)
            return;
        data.objects.remove(o);
        save();
    }
    public void addObject(SmartData o) {
        for(SmartData d : data.objects)
            if(d.id == o.id)
                return;
        data.objects.add(o);
    }

    public boolean hasObject(SmartObject o) {
        for(SmartData d : data.objects)
            if(d.id == o.getId())
                return true;
        return false;
    }
    public SmartData getObject(int id) {
        for(SmartData d : data.objects)
            if((d.id == id))
                return d;
        return null;
    }


*/
