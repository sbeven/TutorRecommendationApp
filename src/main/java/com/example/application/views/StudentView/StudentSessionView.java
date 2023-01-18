package com.example.application.views.StudentView;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;

import javax.annotation.security.PermitAll;

@Route(value = "StudentSessions", layout = StudentMainLayout.class)
@PageTitle("Sessions|Tutor Recommendation App")
@PermitAll
public class StudentSessionView extends Div {

    private final Tab all;
    private final Tab upcoming;
    private final Tab completed;

    private final Tab cancelled;
    private final VerticalLayout content;

    private Button generateSessionForm = new Button("Register for Sessions");

    private Button addSession = new Button("Add Session");

    //private Button createNewSession = new Button("Create New Session");

    private Button edit = new Button("Edit");
    private int count = 0;

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextArea textArea = new TextArea();

    DateTimePicker dateTimePicker = new DateTimePicker("Session date and time");

    Select<String> select = new Select<>();

    public StudentSessionView() {
        all = new Tab("All");
        upcoming = new Tab("Upcoming");
        completed = new Tab("Completed");
        cancelled = new Tab("Cancelled");
        Tabs tabs = new Tabs(all, upcoming, completed, cancelled);
        tabs.addSelectedChangeListener(
                event -> setContent(upcoming));//necessary code
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        content = new VerticalLayout();

        generateSessionForm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        select.setItems("Eula Lane", "Barry Rodriquez");

        setContent(upcoming);

        class sessionListener
                implements ComponentEventListener<ClickEvent<Button>> {

            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                if(firstName.isEmpty() || lastName.isEmpty() || select.isEmpty()){
                    Notification.show("The first three fields must be filled out.");
                } else{
                    firstName.setReadOnly(true);
                    lastName.setReadOnly(true);
                    select.setReadOnly(true);
                    textArea.setReadOnly(true);
                    addSession.setEnabled(false);
                    //register.setEnabled(true);
                    edit.setEnabled(true);
                }
            }
        }

        class editListener
                implements ComponentEventListener<ClickEvent<Button>> {

            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                firstName.setReadOnly(false);
                lastName.setReadOnly(false);
                select.setReadOnly(false);
                textArea.setReadOnly(false);
                addSession.setEnabled(true);
            }
        }

        addSession.addClickListener(new sessionListener());
        edit.addClickListener(new editListener());
        add(generateSessionForm, tabs, content);
    }

    private void setContent(Tab tab) {

        //content.removeAll();

        class registerListener
                implements ComponentEventListener<ClickEvent<Button>> {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {

               content.add(createForm());

            }

        }
        generateSessionForm.addClickListener(new registerListener());
    }


    public FormLayout createForm(){

        select.setLabel("Select Tutor");
        textArea.setLabel("Additional Notes");

        HorizontalLayout buttonLayout = new HorizontalLayout(addSession,
                edit);

        FormLayout formLayout = new FormLayout(firstName, lastName, select,
                dateTimePicker, textArea, buttonLayout);
        formLayout.setResponsiveSteps(
                new ResponsiveStep("0", 2));

        return formLayout;
    }

}