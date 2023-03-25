package com.luni.views.list;

import com.luni.data.entity.CollegeInfo;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class RecommendView extends VerticalLayout {



    private HorizontalLayout searchLayout = new HorizontalLayout();

    private  TextField nameTextField = new TextField();

    private Button findSimilarButton = new Button();

    private VerticalLayout results = new VerticalLayout();

    public RecommendView(){
        nameTextField.setPlaceholder("Type school name...");
        findSimilarButton.setText("Find Similar");
        searchLayout.add(nameTextField);
        searchLayout.add(findSimilarButton);
        add(searchLayout);
        add(results);

        findSimilarButton.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            remove(results);
            //TODO implement the school search
            this.results = new VerticalLayout();
            int totalResults = 5;
            //List<HorizontalLayout> list = new ArrayList<>();
            HorizontalLayout row = new HorizontalLayout();
            int numAcross = 2;
            for(int i = 0; i < totalResults; i++){
                //TODO populate with result college info from algorithm
                CompareSnip cs = new CompareSnip(new CollegeInfo());
                row.add(cs);
                if((i+1) % numAcross == 0){
                    //list.add(row);
                    results.add(row);
                    row = new HorizontalLayout();
                }
            }
            add(results);
        });

        this.add(searchLayout);

        VerticalLayout resultLayout = new VerticalLayout();


    }



}
