package screen;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import control.ViewData;
import jdk.nashorn.internal.objects.Global;
import sh.Globals;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by s on 2/15/16.
 */
public class UserScreen extends VerticalLayout implements Screen {
	ArrayList<ViewData> views;
	public UserScreen() {
		views = Globals.control.getViews();
		this.addComponent(new Label("Nothing yet"));
	}

	@Override
	public void show() {
		this.setVisible(true);
	}

	@Override
	public void hide() {
		this.setVisible(false);
	}

	@Override
	public void alert(String stuff) {

	}
}
