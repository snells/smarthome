package screen;

import com.vaadin.ui.AbsoluteLayout;
import control.ViewData;
import sh.Globals;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by s on 2/15/16.
 */
public class UserScreen extends Screen {
	ArrayList<ViewData> views;
	public UserScreen() {
		addStyleName("userView");
		setSizeFull();
		views = Globals.control.getViews();
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void alert(String stuff) {

	}
}
