package screen;

import java.util.ArrayList;

import com.vaadin.ui.AbsoluteLayout;
import sh.ShUI;
import sun.rmi.runtime.Log;


/**
 * Created by s on 2/15/16.
 */
public class Root extends AbsoluteLayout {
	private Screen currentScreen;
	private UserScreen userScreen;
	private AdminScreen adminScreen;
	private LoginScreen loginScreen;
	private UserSelect userSelect;
	private ShUI ui;

	public Root(ShUI ui) {
		this.ui = ui;
		this.setSizeFull();
		this.addStyleName("root");

		userScreen = new UserScreen();
		adminScreen = new AdminScreen();
		loginScreen = new LoginScreen();
		userSelect = new UserSelect();

		userScreen.setSizeFull();
		adminScreen.setSizeFull();
		loginScreen.setSizeFull();
		userSelect.setSizeFull();

		userScreen.addStyleName("screen");
		adminScreen.addStyleName("screen");
		loginScreen.addStyleName("screen");
		userSelect.addStyleName("screen");


		userScreen.setVisible(false);
		adminScreen.setVisible(false);
		loginScreen.setVisible(false);
		userSelect.setVisible(true);

		this.addComponents(loginScreen, userScreen, adminScreen, userSelect);
	}
	


	public void alert(String text) {
		currentScreen.alert(text);
	}

	private Screen getScreen(Screen.TYPE sc) {
		switch (sc) {
			case LOGIN: return loginScreen;
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


}
