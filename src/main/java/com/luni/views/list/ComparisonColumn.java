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

    public ComparisonColumn(CrmService service){
        nameTextField.setPlaceholder("Type school name...");
        Button addBuutton = new Button();
        addBuutton.setText("Add");

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.add(nameTextField);
        searchLayout.add(addBuutton);
        layout.add(searchLayout);

        addBuutton.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
        	layout.removeAll();
        	layout.add(searchLayout);
            List<CollegeInfo> colleges = service.getCollegeInfosByName(nameTextField.getValue(), 1);
            if(colleges != null && !colleges.isEmpty()){
                CompareSnip compareSnip = new CompareSnip(colleges.get(0));
                layout.add(compareSnip);
                layout.add(compareSnip.getComparisonContent());
            }
        });

    }

    public VerticalLayout getComparisonContent(){
        return this.layout;
    }

}
