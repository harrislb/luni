package com.luni.views.list;

import com.luni.connection.ConnectionManager;
import com.luni.data.entity.CollegeInfo;
import com.luni.data.entity.Contact;
import com.luni.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "")
@PageTitle("Contacts | Vaadin CRM")
public class ListView extends VerticalLayout {
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();
    TextField filterLocText = new TextField();
    CheckboxGroup<String> checkboxSizeGroup = new CheckboxGroup<>();
    CheckboxGroup<String> checkboxTuitionGroup = new CheckboxGroup<>();
    CrmService service;
    Image roseImage = new Image();
    Image tuImage = new Image();

    VerticalLayout snapsContainer = new VerticalLayout();
    List<HorizontalLayout> uniSnaps;
   

    public ListView(CrmService service) {
        ConnectionManager.loadAPI_Key();
        Image img = new Image("images/luni.png", "banner logo");
        img.setWidth("100%");
        add(img);

        snapsContainer.setHeightFull();


//        roseImage.setSrc("images/rose.png");
//        roseImage.setWidth("300px");
//        roseImage.setHeightFull();
//        tuImage.setSrc("images/tu.jpg");
//        tuImage.setWidth("300px");
//        tuImage.setHeightFull();
//        HorizontalLayout collegePics = new HorizontalLayout(roseImage, tuImage);

        //add(getToolbar(), getContent(), getFilters());
        


        this.service = service;
        this.uniSnaps = getUniSnaps();
        for(HorizontalLayout layout : this.uniSnaps){
            snapsContainer.add(layout);
        }
        
        //add(snapsContainer);
        add(renderContent(), getToolbar());
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

//        add(collegePics, getToolbar(), getContent());
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

    private List<HorizontalLayout> getUniSnaps() {
        List<CollegeInfo> colleges = service.getCollegeInfos();
        return toSnaps(colleges);
    }

    private List<HorizontalLayout> toSnaps(List<CollegeInfo> collegeInfos){
        List<HorizontalLayout> list = new ArrayList<>();
        HorizontalLayout uniSnaps = new HorizontalLayout();
        uniSnaps.addClassName("uniSnaps");
        int numAcross = 5;
        int current = 1;
        for(CollegeInfo college : collegeInfos){
            UniSnip uniSnip = new UniSnip(college);
            uniSnaps.add(uniSnip);
            if(current % numAcross == 0){
                list.add(uniSnaps);
                uniSnaps = new HorizontalLayout();
                uniSnaps.addClassName("uniSnaps");
            }
            current++;
        }
        list.add(uniSnaps);
        return list;
    }

    private HorizontalLayout getToolbar() {

        Button searchButton = new Button("Search");
        searchButton.addClickListener(clickEvent -> {
           clearUniSnaps();
           String nameSearch = filterText.getValue();
           String locSearch = filterLocText.getValue();
           List<CollegeInfo> collegeInfos = new ArrayList<>();
           if(!nameSearch.isEmpty()){
               collegeInfos = service.getCollegeInfosByName(nameSearch);
           }
           else if(!locSearch.isEmpty()){
               collegeInfos = service.getCollegeInfosByLoc(locSearch);
           }
           else if(!checkboxSizeGroup.getSelectedItems().isEmpty()){
               System.out.println("checking size group");
               int[] minMax = getMinMax(checkboxSizeGroup);
               collegeInfos = service.getCollegeInfosBySize(minMax[0], minMax[1]);
           }
           this.uniSnaps = toSnaps(collegeInfos);
           addUniSnaps(this.uniSnaps);
        });

        Button clearButton = new Button("Clear Search");
        clearButton.addClickListener(clickEvent -> {
            filterText.clear();
            filterLocText.clear();
        });

        HorizontalLayout toolbar = new HorizontalLayout(searchButton, clearButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    private HorizontalLayout renderContent() {
        HorizontalLayout content = new HorizontalLayout(getFilters(), snapsContainer);
        content.addClassName("mainpage");
        return content;
    }
    
    private VerticalLayout getFilters() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    	
    	filterLocText.setPlaceholder("Filter by location...");
        filterLocText.setClearButtonVisible(true);
        filterLocText.setValueChangeMode(ValueChangeMode.LAZY);
        filterLocText.addValueChangeListener(e -> updateList());
        
        checkboxSizeGroup.setLabel("Size");
        checkboxSizeGroup.setItems("0-499", "500-999", "1000-4999",
                "5000-14999", "15000-34999", "35000-54999", "55000+");
        //checkboxSizeGroup.select("Order ID", "Customer");
        checkboxSizeGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        add(checkboxSizeGroup);
        
        checkboxTuitionGroup.setLabel("Tuition Per Semester");
        checkboxTuitionGroup.setItems("In state?", "0-499", "500-999", "1000-4999",
                "5000-14999", "15000-24999", "25000+");
        //checkboxSizeGroup.select("Order ID", "Customer");
        checkboxTuitionGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        add(checkboxTuitionGroup);
        
    	VerticalLayout filters = new VerticalLayout(filterText, filterLocText, checkboxSizeGroup,checkboxTuitionGroup);
    	filters.addClassName("filters");
    	return filters;
    }

    private int[] getMinMax(CheckboxGroup<String> cbg){
        int[] minMax = new int[2];
        int min = 999999999;
        int max = -1;
        for(String s : cbg.getSelectedItems()){
            String[] split = s.split("-");
            for(String sp : split){
                int i = Integer.parseInt(sp);
                if(i < min){
                    min = i;
                }
                if(i > max){
                    max = i;
                }
            }
        }
        minMax[0] = min;
        minMax[1]= max;
        return minMax;
    }
    
    private void clearUniSnaps(){
        this.snapsContainer.removeAll();
    }

    private void addUniSnaps(List<HorizontalLayout> layouts){
        for(HorizontalLayout layout : layouts){
            this.snapsContainer.add(layout);
        }
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