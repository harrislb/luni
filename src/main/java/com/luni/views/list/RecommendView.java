package com.luni.views.list;

import com.luni.data.entity.CollegeInfo;
import com.luni.data.service.CrmService;
import com.luni.data.service.NearestNeighbor;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;

public class RecommendView extends VerticalLayout {
    private final TextField nameTextField = new TextField();
    private VerticalLayout results = new VerticalLayout();

    public RecommendView(CrmService service){
        nameTextField.setPlaceholder("Type school name...");
        Button findSimilarButton = new Button();
        findSimilarButton.setText("Find Similar");
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.add(nameTextField);
        searchLayout.add(findSimilarButton);
        add(searchLayout);
        add(results);


        findSimilarButton.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            remove(results);

            final List<CollegeInfo> collegeInfos = new ArrayList<>();

            // retrieve college info for given school
            CollegeInfo sourceCollege = service.retrieveCollege(nameTextField.getValue());

            if(sourceCollege == null){
                collegeInfos.addAll(service.verifyResults(collegeInfos));
            }
            else{
                String neighbors =  NearestNeighbor.retrieveNearestNeighbors(sourceCollege.getOutOfStateCost() + "");
                String[] vals = neighbors.split(",[ ]");

                for(String val : vals){
                    CollegeInfo match = service.retrieveCollege(val);
                    if(match != null && !match.getName().equals(sourceCollege.getName())){
                        collegeInfos.add(match);
                    }
                }
                service.verifyResults(collegeInfos);
            }

            this.results = new VerticalLayout();
            int totalResults = collegeInfos.size();
            HorizontalLayout row = new HorizontalLayout();
            int numAcross = 3;
            boolean addedToResults = false;
            for(int i = 0; i < totalResults; i++){
                CompareSnip cs = new CompareSnip(collegeInfos.get(i));
                row.add(cs);
                addedToResults = false;
                if((i+1) % numAcross == 0){
                    results.add(row);
                    addedToResults = true;
                    row = new HorizontalLayout();
                }
            }
            if(!addedToResults){
                results.add(row);
            }
            add(results);
        });

        this.add(searchLayout);
    }
}
