package screen;

import java.util.ArrayList;

/**
 * Created by s on 2/15/16.
 */
public class Root {
    Screen currentScreen;

    enum SCREEN { MAIN, LOGIN, ADMIN, USER };

    ArrayList<Screen> screens;

    public void changeScreen(SCREEN  sc) {
        //currentScreen.hide();
        //getScreen(sc).show();

    }


}
