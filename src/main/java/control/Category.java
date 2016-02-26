package control;


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

    public ArrayList<SmartData> getObjects() {
        return data.objects;

    }

    public String getName() {
        return data.name;
    }
}
