package com.example.application.views;

import com.example.application.data.entity.Status;
import com.example.application.data.entity.Contact;
import java.util.HashSet;
import java.util.Set;

import com.example.application.data.service.CrmService;
import com.example.application.security.SecurityConfig;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("register")
@PageTitle("Register | Sample App")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
	private TextField first = new TextField("First Name");
	private TextField last = new TextField("Last Name");
	private TextField username = new TextField("Username");
	private TextField password = new TextField("Password");
	private EmailField email = new EmailField("Email");
	private ComboBox<String> roles = new ComboBox<>("Sign up as");
	private MultiSelectComboBox<String> subject = new MultiSelectComboBox<>("Subjects (if tutor)");
	private Button signup = new Button("Sign up");
	private Button back = new Button("Cancel");
	private CrmService service;

	public RegisterView(CrmService service) {
		this.service = service;
		subject.setItems(new String[]{"English", "Math", "Social Studies", "Science", "Art", "Music"});
		roles.setItems(new String[]{"Student", "Tutor"});
		signup.addClickListener(
				e -> signup.getUI().ifPresent(ui -> signUp(ui)));
		back.addClickListener(e -> back.getUI().ifPresent(ui -> ui.navigate("")));
		FormLayout form = new FormLayout(first, last, username, password, email, roles, subject, signup, back);
		form.setColspan(email, 2);
		add(form);
		setJustifyContentMode(JustifyContentMode.CENTER);
	}
	public void signUp(UI navigation){

		String user = username.getValue();
		String pass = password.getValue();
		String role = roles.getValue();
		String f = first.getValue();
		String l = last.getValue();
		String e = email.getValue();
		Set<String> set = subject.getValue();
		String s = "";
		for (String str : set) {
			s += str + ", ";
		}
		s = s.substring(0, s.length() - 2);
		if(pass.trim().isEmpty() || user.trim().isEmpty()){
			Notification.show("You must fill in all necessary fields");
			navigation.navigate("register");
		} else if (role == "Tutor") {
			SecurityConfig.addUser(user, pass, role);
			Contact c = new Contact();
			c.setFirstName(f);
			c.setLastName(l);
			c.setEmail(e);
			c.setSubjects(s);
			service.saveContact(c);
			navigation.navigate("login");
		} else {
			SecurityConfig.addUser(user, pass, role);
			navigation.navigate("login");
		}
	}
}

