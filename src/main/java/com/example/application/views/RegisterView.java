package com.example.application.views;

import com.example.application.security.SecurityConfig;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.core.userdetails.UserDetailsService;

@Route("register")
@PageTitle("Register | Sample App")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
	private TextField username = new TextField("Username");
	private TextField password = new TextField("Password");
	private ComboBox<String> roles = new ComboBox<>("Sign up as");
	private Button signup = new Button("Sign up");
	private Button back = new Button("Cancel");
	public RegisterView() {
		roles.setItems(new String[]{"Student", "Tutor"});
		signup.addClickListener(e -> signup.getUI().ifPresent(ui -> signUp(ui)));
		back.addClickListener(e -> back.getUI().ifPresent(ui -> ui.navigate("")));
		FormLayout form = new FormLayout(username, password, roles, signup, back);
		add(form);
		setJustifyContentMode(JustifyContentMode.CENTER);
	}
	public void signUp(UI navigation){
		String user = username.getValue();
		String pass = password.getValue();
		String role = roles.getValue();
		SecurityConfig.addUser(user, pass, role);
		navigation.navigate("");
	}
}

