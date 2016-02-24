package control;


public class View {
    private ViewData data;

    public View(String name, String house) {
        data = new ViewData(name, house);
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
