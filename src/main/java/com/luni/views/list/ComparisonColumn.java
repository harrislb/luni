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

    private VerticalLayout layout = new VerticalLayout();

    private TextField nameTextField = new TextField();
    private Button addBuutton = new Button();
    private UniSnip uniSnip;

    private CrmService service;

    public ComparisonColumn(CrmService service){
        this.service = service;
        nameTextField.setPlaceholder("Type school name...");
        addBuutton.setText("Add");

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.add(nameTextField);
        searchLayout.add(addBuutton);
        layout.add(searchLayout);

        addBuutton.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            List<CollegeInfo> colleges = service.getCollegeInfosByName(nameTextField.getValue());
            if(colleges != null && !colleges.isEmpty()){
                CompareSnip compareSnip = new CompareSnip(colleges.get(0));
                layout.add(compareSnip);
            }
        });

    }

    public VerticalLayout getComparisonContent(){
        return this.layout;
    }

}
