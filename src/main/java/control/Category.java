package control;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category {
    private CategoryData data;

    public Category(CategoryData d) {
        data = d;
    }

    public static ArrayList<Category> genCategories(ArrayList<CategoryData> objects) {
        ArrayList<Category> c = new ArrayList<>();
        objects.forEach(e -> c.add(new Category(e)));
        return c;
    }

    public CategoryData getData() {
        return data;
    }

    public boolean hasObject(SmartObject o ){
        for(SmartData d : data.objects)
            if(d.id == o.getId())
                return true;
        return false;
    }
    public ArrayList<SmartObject> getObjects() {
        return SmartObject.genObjects(data.objects);

    }

    public static Category getCategory(ArrayList<Category> cats, String name) {
        for(Category e : cats)
            if(e.getName().equals(name))
                return e;
        return null;

    }

    public ArrayList<String> getObjectNames() {
        return SmartObject.getRawNames(data.objects);
    }

    public static ArrayList<String> getNames(ArrayList<Category> cats) {
        ArrayList<String> names = new ArrayList<>();
        cats.forEach(c -> names.add(c.getName()));
        return names;
    }

    public static boolean unique(ArrayList<Category> cat, String name) {
        for(Category c :cat)
            if(c.getName().equals(name))
                return false;
        return true;
    }

    public void addObject(SmartObject o) {
        for(SmartData d : data.objects)
            if(d.id == o.getId())
                return;
        data.objects.add(o.getData());
    }

    public void removeObject(SmartObject o) {
        if(data.objects.contains(o.getData()))
            data.objects.remove(o.getData());
    }

    public void renameObject(int id, String name) {
        for(SmartData d : data.objects)
            if(d.id == id)
                d.name = name;
    }

    public void setname(String name) {
        data.name = name;
    }

    public String getName() {
        return data.name;
    }
}
