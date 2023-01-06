package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "Sessions", layout = MainLayout.class)
@PageTitle("Sessions| Tutor Recommendation App")
@PermitAll
public class SessionView extends VerticalLayout {
    private final CrmService service;

    public SessionView(CrmService service) {
        this.service = service;
        addClassName("sessions-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getContactStats());
    }

    private Component getContactStats() {
        Span stats = new Span(service.countContacts() + " contacts");
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

}