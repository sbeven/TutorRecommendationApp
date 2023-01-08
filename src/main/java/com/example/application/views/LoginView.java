package com.example.application.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Sample App")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private final LoginOverlay login = new LoginOverlay();

	public LoginView(){

		addClassName("login-view");
		LoginOverlay loginOverlay = new LoginOverlay();
		loginOverlay.setTitle("Tutor Recommendation App");
		loginOverlay.setDescription("Created by Scott Chen and Steven Zhang \n Login with username" +
				" 'user' and password 'userpass.'");
		loginOverlay.setOpened(true);
		loginOverlay.setAction("login");
		add(loginOverlay);

	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if(beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")) {
			login.setError(true);
		}
	}
}