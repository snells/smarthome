package screen;

import java.util.ArrayList;

import com.vaadin.ui.AbsoluteLayout;
import control.User;
import control.UserData;
import sh.Globals;
import sh.ShUI;
import sun.rmi.runtime.Log;


/**
 * Created by s on 2/15/16.
 */
public class Root extends AbsoluteLayout {
	private Screen currentScreen;
	private UserScreen userScreen;
	private AdminScreen adminScreen;
	private UserSelect userSelect;
	private ShUI ui;

	public Root(ShUI ui) {
		this.ui = ui;
		this.setSizeFull();
		this.addStyleName("root");

		userScreen = new UserScreen();
		adminScreen = new AdminScreen();
		userSelect = new UserSelect();

		userScreen.setSizeFull();
		adminScreen.setSizeFull();
			userSelect.setSizeFull();

		userScreen.addStyleName("screen");
		adminScreen.addStyleName("screen");
			userSelect.addStyleName("screen");


		userScreen.setVisible(false);
		adminScreen.setVisible(false);
		userSelect.setVisible(true);

		this.addComponents(userScreen, adminScreen, userSelect);

		currentScreen = userSelect;
		userSelect.show();

	}
	


	public void alert(String text) {
		currentScreen.alert(text);
	}

	private Screen getScreen(Screen.TYPE sc) {
		switch (sc) {
			case USER_SELECT: return userSelect;
			case ADMIN: return adminScreen;
			case USER: return userScreen;
			default: throw new RuntimeException("ERROR trying to get screen that does not exists");
		}
	}

    public void changeScreen(Screen.TYPE sc) {
		currentScreen.hide();
		currentScreen = getScreen(sc);
		currentScreen.show();
    }

	public void changeScreen(User user) {
		currentScreen.hide();
		currentScreen = getScreen((user.getRight() == User.RIGHT.ADMIN) ? Screen.TYPE.ADMIN : Screen.TYPE.USER);
		currentScreen.show();
	}


}
