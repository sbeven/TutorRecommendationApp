package com.example.application.views.TutorView;

import com.example.application.data.service.CrmService;
import com.example.application.security.SecurityService;
import com.example.application.views.StudentView.StudentMainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.annotation.security.PermitAll;

@Route(value = "TutorSessions", layout = TutorMainLayout.class)
@PageTitle("TutorSessions|Tutor Recommendation App")
@PermitAll
public class TutorSessionView extends Div {
    private final CrmService service;
    private final SecurityService securityService;
    public TutorSessionView(CrmService service, SecurityService security) {
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