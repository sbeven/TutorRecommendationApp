package com.example.application.views;

import com.example.application.views.list.ListView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.firitin.util.WebStorage;

@Route("login")
@PageTitle("Login | Sample App")
public class LoginView extends Div implements BeforeEnterObserver, BeforeLeaveObserver {

	private final LoginOverlay login = new LoginOverlay();

	public LoginView(){

		addClassName("login-view");
		LoginOverlay loginOverlay = new LoginOverlay();
		loginOverlay.setTitle("Tutor Management System");
		loginOverlay.setDescription("Created by Scott Chen and Steven Zhang \n Tutor Username: t Password: t. " +
				"Student Username: s Password: s");
		loginOverlay.setForgotPasswordButtonVisible(false);
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
			login.setError(false);
		}
	}

	@Override
	public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {
		System.out.println("leaving login");
	}
}