package control;


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

    public ArrayList<SmartObject> getObjects() {
        return SmartObject.genObjects(data.objects);

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

    public void addObject(SmartData o) {
        for(SmartData d : data.objects)
            if(d.id == o.id)
                return;
        data.objects.add(o);
    }

    public SmartData getObject(int id) {
        for(SmartData d : data.objects)
            if((d.id == id))
                return d;
        return null;
    }
    public void removeObject(SmartData o) {
        SmartData d = getObject(o.id);
        if(d == null)
            return;
        data.objects.remove(o);
    }

    public CategoryData getCat(String name) {
        for(CategoryData d : data.categories)
            if(d.name.equals(name))
                return d;
        return null;
    }

    public void addCat(String name) {
        if(getCat(name) == null)
            data.categories.add(new CategoryData(name));
    }
    public void removeCat(String name) {
        CategoryData d = getCat(name);
        if(d != null)
            data.objects.remove(d);
    }

    public String getName() {
        return data.name;
    }

}
