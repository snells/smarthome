package screen;


import com.vaadin.ui.AbsoluteLayout;

public interface Screen {
    public enum TYPE { LOGIN, ADMIN, USER, USER_SELECT };

    //public abstract void init(User user);

    public abstract void show();

    public abstract void hide();

    public abstract void update();
    //public abstract void alert(String stuff);
}


