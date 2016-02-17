package screen;


import com.vaadin.ui.AbsoluteLayout;

public abstract class Screen extends AbsoluteLayout {
    public enum TYPE { LOGIN, ADMIN, USER, USER_SELECT };

    //public abstract void init(User user);

    public abstract void show();

    public abstract void hide();

    public abstract void alert(String stuff);
}


