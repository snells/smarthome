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
	UserView view;
	private User user = null;
	public UserScreen() {
		view = null;
	}

	@Override
	public void show() {
		this.removeAllComponents();
		this.setVisible(true);
		user = Globals.user;
		view = new UserView(user);
		view.setSizeFull();
		this.addComponent(view);
	}

	@Override
	public void hide() {
		this.setVisible(false);
	}

	public void update() {

	}
}
