package screen;

import com.vaadin.ui.*;
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
	private HorizontalLayout topBar;
	public UserScreen() {
		topBar = new HorizontalLayout();
		topBar.setSizeFull();
		Button b = new Button("logout");
		b.addClickListener(e -> Globals.control.logout());
		topBar.addComponent(b);
		b.setHeight("100%");
		topBar.setComponentAlignment(b, Alignment.MIDDLE_RIGHT);
	}

	@Override
	public void show() {
		this.removeAllComponents();
		this.setVisible(true);
		user = Globals.user;
		view = new UserView(user);
		view.setSizeFull();
		this.addComponents(topBar,view);
		setExpandRatio(topBar, 0.1f);
		setExpandRatio(view, 0.9f);
		if(user.tips()) view.tip();
	}

	@Override
	public void hide() {
		this.setVisible(false);
	}

	public void update() {

	}
}
