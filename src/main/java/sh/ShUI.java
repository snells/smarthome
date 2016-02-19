package sh;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

import control.Control;
import screen.Root;

import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("shtheme")
@Widgetset("sh.ShWidgetset")
public class ShUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Globals g = new Globals();
        g.ui = this;
        Globals.control = new Control();
        Globals.control.init();
        Root root = new Root(this);
        Globals.root = root;
        setContent(root);
    }

    @WebServlet(urlPatterns = "/*", name = "ShUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ShUI.class, productionMode = false)
    public static class ShUIServlet extends VaadinServlet {
    }
}
