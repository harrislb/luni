package com.luni.views.list;

import com.luni.connection.ConnectionManager;
import com.luni.data.entity.CollegeInfo;
import com.luni.data.entity.Contact;
import com.luni.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "")
@PageTitle("Contacts | Vaadin CRM")
public class ListView extends VerticalLayout {
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();
    CrmService service;
    Image roseImage = new Image();
    Image tuImage = new Image();

    HorizontalLayout uniSnaps;


    public ListView(CrmService service) {
        ConnectionManager.loadAPI_Key();
        Image img = new Image("images/luni.png", "banner logo");
        img.setWidth("100%");
        add(img);


//        roseImage.setSrc("images/rose.png");
//        roseImage.setWidth("300px");
//        roseImage.setHeightFull();
//        tuImage.setSrc("images/tu.jpg");
//        tuImage.setWidth("300px");
//        tuImage.setHeightFull();
//        HorizontalLayout collegePics = new HorizontalLayout(roseImage, tuImage);



        this.service = service;
        this.uniSnaps = getUniSnaps();
        add(this.uniSnaps);

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

//        add(collegePics, getToolbar(), getContent());
        add(getToolbar(), getContent());
        updateList();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {

    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        //grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        //grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getUniSnaps() {
        List<CollegeInfo> colleges = service.getCollegeInfos();
        return toSnaps(colleges);


//        HorizontalLayout uniSnaps = new HorizontalLayout();
//        List<CollegeInfo> colleges = service.getCollegeInfos();
//        for(CollegeInfo college : colleges){
//            UniSnip uniSnip = new UniSnip(college);
//            uniSnaps.add(uniSnip);
//        }
//
//
//       // add(uniSnip);
//
//
//        uniSnaps.addClassName("uniSnaps");
//        return uniSnaps;
    }

    private HorizontalLayout toSnaps(List<CollegeInfo> collegeInfos){
        HorizontalLayout uniSnaps = new HorizontalLayout();
        for(CollegeInfo college : collegeInfos){
            UniSnip uniSnip = new UniSnip(college);
            uniSnaps.add(uniSnip);
        }
        uniSnaps.addClassName("uniSnaps");
        return uniSnaps;
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button searchNameButton = new Button("Search");
        searchNameButton.addClickListener(clickEvent -> {
           remove(this.uniSnaps);
           String nameSearch = filterText.getValue();
           List<CollegeInfo> collegeInfos = service.getCollegeInfosByName(nameSearch);
           this.uniSnaps = toSnaps(collegeInfos);
           add(this.uniSnaps);
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText, searchNameButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }

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