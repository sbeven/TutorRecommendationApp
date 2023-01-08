package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.example.application.security.SecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.annotation.security.PermitAll;

@Route(value = "Sessions", layout = MainLayout.class)
@PageTitle("Sessions|Tutor Recommendation App")
@PermitAll
public class SessionView extends Div {
    private final CrmService service;
    private final SecurityService securityService;
    public SessionView(CrmService service, SecurityService security) {
        this.service = service;
        this.securityService = security;
        addClassName("session-view");
        createSessionView();
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab("All"), createTab("Upcoming"),
                createTab("Completed"), createTab("Cancelled"));
        return tabs;
    }

    private Tab createTab(String viewName) {
        RouterLink link = new RouterLink();
        link.add(viewName);


        return new Tab(link);
    }


    public void createSessionView() {
        H1 logo = new H1("Sessions");

        logo.addClassNames("text-l", "m-m");

        //Button logout = new Button("Log out", e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(logo);

        Tabs tabs = getTabs();
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);

        add(header, tabs);
    }



}