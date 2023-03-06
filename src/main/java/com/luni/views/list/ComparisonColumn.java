package com.luni.views.list;


import com.luni.data.entity.CollegeInfo;
import com.luni.data.service.CrmService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ComparisonColumn {

    private final VerticalLayout layout = new VerticalLayout();

    private final TextField nameTextField = new TextField();

    private Button addButton = new Button();
    private HorizontalLayout searchLayout = new HorizontalLayout();
    private CrmService service;

    private CompareSnip compareSnip;


    public ComparisonColumn(CrmService service){
        this.service = service;
        nameTextField.setPlaceholder("Type school name...");
        addButton.setText("Add");

        searchLayout.add(nameTextField);
        searchLayout.add(addButton);
        layout.add(searchLayout);

        addButton.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            addButtonEventHandler();
        });

    }

    private void addButtonEventHandler(){
        layout.removeAll();
        layout.add(searchLayout);
        List<CollegeInfo> colleges = service.getCollegeInfosByName(nameTextField.getValue(), 1);
        if(colleges != null && !colleges.isEmpty()){
            CompareSnip compareSnip = new CompareSnip(colleges.get(0));
            layout.add(compareSnip);
            layout.add(compareSnip.getComparisonContent());
            this.compareSnip = compareSnip;

        }
    }

    public VerticalLayout getComparisonContent(){
        return this.layout;
    }

    public void setNameValue(String name){
        this.nameTextField.setValue(name);
    }

    protected void clickAddButton(){
        addButtonEventHandler();
    }

    public CompareSnip getCompareSnip(){
        return this.compareSnip;
    }
}
