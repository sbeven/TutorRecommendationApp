package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.example.application.security.SecurityConfig;
import com.example.application.security.SecurityService;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import org.springframework.security.core.Authentication;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import org.springframework.security.core.context.SecurityContextHolder;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.login.LoginOverlay;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@Route("")
@PageTitle("Tutor Recommendation App")
@AnonymousAllowed
public class StarterView extends VerticalLayout implements BeforeEnterObserver {
    public StarterView() {
        H1 a = new H1("Welcome!");
        add(a);
        Button button = new Button("Login");
        button.addClickListener(e -> button.getUI().ifPresent(ui -> ui.navigate("login")));
        add(button);
        Button signup = new Button("Sign up");
        signup.addClickListener(e -> signup.getUI().ifPresent(ui -> ui.navigate("register")));
        add(signup);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TUTOR"))) {
            System.out.println("go to session");
            beforeEnterEvent.forwardTo(SessionView.class);
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
            System.out.println("go to list");
            beforeEnterEvent.forwardTo(ListView.class);
        }
    }
}