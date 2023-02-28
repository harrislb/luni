package com.luni.views.list;

import com.luni.data.entity.CollegeInfo;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CompareSnip extends UniSnip{

    private final Text size = new Text("Size: ");
    private final Text act = new Text("Midpoint Cumulative ACT Score: ");
    private final Text cost = new Text("Cost: " );
    private VerticalLayout layout = new VerticalLayout();
    private HorizontalLayout costLayout = new HorizontalLayout();
    private HorizontalLayout sizeLayout = new HorizontalLayout();
    private HorizontalLayout actLayout = new HorizontalLayout();

    private Text sizeValue;
    private Text actValue;
    private Text costValue;

    public CompareSnip(CollegeInfo collegeInfo) {
        super(collegeInfo);

        this.sizeValue = new Text(collegeInfo.getSize() + "");
        //add(size);
        //add(sizeValue);

        this.actValue = new Text(collegeInfo.getACT() + "");
        //add(act);
        //add(actValue);

        // TODO account for in vs out of state cost
        this.costValue = new Text(collegeInfo.getOutOfStateCost() + "");
        //add(cost);
        //add(costValue);
        
        costLayout.add(cost);
        costLayout.add(costValue);
        
        sizeLayout.add(size);
        sizeLayout.add(sizeValue);
        
        actLayout.add(act);
        actLayout.add(actValue);
        
        layout.add(costLayout);
        layout.add(sizeLayout);
        layout.add(actLayout);
        
    }
    public VerticalLayout getComparisonContent(){
        return this.layout;
    }
}

