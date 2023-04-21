package com.luni.views.list;

import com.luni.data.entity.CollegeInfo;
import com.luni.data.service.CrmService;
import com.luni.data.service.NearestNeighbor;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;


public class RecommendView extends VerticalLayout {



    private HorizontalLayout searchLayout = new HorizontalLayout();

    private  TextField nameTextField = new TextField();

    private Button findSimilarButton = new Button();

    private VerticalLayout results = new VerticalLayout();

    private CrmService service;

    ProgressBar progressBar = new ProgressBar();
    Div progressBarLabel = new Div();


    public RecommendView(CrmService service){
        this.service = service;
        nameTextField.setPlaceholder("Type school name...");
        findSimilarButton.setText("Find Similar");
        searchLayout.add(nameTextField);
        searchLayout.add(findSimilarButton);
        add(searchLayout);
        add(results);

        findSimilarButton.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            remove(results);

            progressBar.setIndeterminate(true);

            progressBarLabel.setText("Finding similar schools...");
            add(progressBarLabel, progressBar);


            // retrieve college info for given school
            CollegeInfo sourceCollege = service.retrieveCollege(nameTextField.getValue());

            List<CollegeInfo> collegeInfos = new ArrayList<>();

            if(sourceCollege == null){
                collegeInfos = service.verifyResults(collegeInfos);
            }
            else{
                System.out.println("Looking for matches for school: " + sourceCollege.getName());
                System.out.println("Cost of school is: " + sourceCollege.getOutOfStateCost());
                String neighbors =  NearestNeighbor.retrieveNearestNeighbors(sourceCollege.getOutOfStateCost() + "");
                System.out.println("neighbors: " + neighbors);
                String[] vals = neighbors.split(",[ ]");

                for(String val : vals){
                    System.out.println("val : " + val);
                    CollegeInfo match = service.retrieveCollege(val);
                    if(match != null && !match.getName().equals(sourceCollege.getName())){
                        collegeInfos.add(match);
                    }
                }

                collegeInfos = service.verifyResults(collegeInfos);

            }

            remove(progressBarLabel, progressBar);
            this.results = new VerticalLayout();
            int totalResults = collegeInfos.size();
            //List<HorizontalLayout> list = new ArrayList<>();
            HorizontalLayout row = new HorizontalLayout();
            int numAcross = 3;
            boolean addedToResults = false;
            for(int i = 0; i < totalResults; i++){
                CompareSnip cs = new CompareSnip(collegeInfos.get(i));
                row.add(cs);
                row.add(cs.getComparisonContent());

                addedToResults = false;
                if((i+1) % numAcross == 0){
                    //list.add(row);
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
