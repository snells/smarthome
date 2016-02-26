package screen;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import control.User;
import control.View;
import control.ViewData;
import jdk.nashorn.internal.objects.Global;
import sh.Globals;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;


public class UserScreen extends VerticalLayout implements Screen {
	ArrayList<View> views;
	private User user = null;
	public UserScreen() {
		views = Globals.control.getViews();
		this.addComponent(new Label("Nothing yet"));
	}

	@Override
	public void show() {
		this.setVisible(true);
		user = Globals.user;

	}

	@Override
	public void hide() {
		this.setVisible(false);
	}

	@Override
	public void alert(String stuff) {

	}
}
