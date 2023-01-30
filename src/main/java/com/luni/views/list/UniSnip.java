package com.luni.views.list;

import com.luni.data.entity.CollegeInfo;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "1")
@PageTitle("Contacts | Vaadin CRM")
public class UniSnip extends VerticalLayout {
    Text nameText = new Text("");
    Image collegeImage;
    Text locText = new Text("");

    public UniSnip(CollegeInfo collegeInfo) {

        //setWidth("");

        nameText.setText(collegeInfo.getName());
        add(nameText);


       collegeImage= new Image(collegeInfo.getURL(), "college img");
//        collegeImage.setWidth("100%");
        collegeImage.setWidth("300px");
        collegeImage.setHeightFull();
        add(collegeImage);

        locText.setText(collegeInfo.getLocation());
        add(locText);


        addClassName("unisnip");
        setSizeFull();



//        configureGrid();
//        configureForm();
//
//        add(collegePics, getToolbar(), getContent());
//        updateList();
    }

//    private Component getContent() {
//        HorizontalLayout content = new HorizontalLayout(grid);
//        content.setFlexGrow(2, grid);
//        content.setFlexGrow(1);
//        content.addClassNames("content");
//        content.setSizeFull();
//        return content;
//    }
//
//    private void configureForm() {
//
//    }

//    private void configureGrid() {
//        grid.addClassNames("contact-grid");
//        grid.setSizeFull();
//        grid.setColumns("firstName", "lastName", "email");
//        //grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
//        //grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
//        grid.getColumns().forEach(col -> col.setAutoWidth(true));
//    }
//
//    private HorizontalLayout getToolbar() {
//        filterText.setPlaceholder("Filter by name...");
//        filterText.setClearButtonVisible(true);
//        filterText.setValueChangeMode(ValueChangeMode.LAZY);
//        filterText.addValueChangeListener(e -> updateList());
//
//        Button addContactButton = new Button("Add contact");
//
//        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
//        toolbar.addClassName("toolbar");
//        return toolbar;
//    }
//
//    private void updateList() {
//        grid.setItems(service.findAllContacts(filterText.getValue()));
//    }

    /**
     *
     *   public ListView() {
     *         setSpacing(false);
     *
     *         Image img = new Image("images/empty-plant.png", "placeholder plant");
     *         img.setWidth("200px");
     *         add(img);
     *
     *         add(new H2("This place intentionally left empty"));
     *         add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
     *
     *         setSizeFull();
     *         setJustifyContentMode(JustifyContentMode.CENTER);
     *         setDefaultHorizontalComponentAlignment(Alignment.CENTER);
     *         getStyle().set("text-align", "center");
     *     }
     */
}