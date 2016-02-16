package screen;


import control.ViewData;
import sh.User;

public abstract class Screen {  // extends StackLayout

    //public abstract void init(User user);

    public abstract void show(User user, ViewData vd);

    public abstract void hide();

    public abstract void alert(String stuff);

    public abstract void logout();

    public abstract void login(User user);

}


