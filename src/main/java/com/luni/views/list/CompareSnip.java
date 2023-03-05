package com.luni.views.list;

import com.luni.data.entity.CollegeInfo;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CompareSnip extends UniSnip{

    private final Text size = new Text("Size: ");
    private final Text act = new Text("Midpoint Cumulative ACT Score: ");
    private final Text inStateCost = new Text("In State Cost: " );
    private final Text outStateCost = new Text("Out of State Cost: " );

    private VerticalLayout layout = new VerticalLayout();
    private HorizontalLayout inStateCostLayout = new HorizontalLayout();
    private HorizontalLayout outStateCostLayout = new HorizontalLayout();
    private HorizontalLayout sizeLayout = new HorizontalLayout();
    private HorizontalLayout actLayout = new HorizontalLayout();

    private Text sizeValue;
    private Text actValue;
    private Text inStateCostValue;
    private Text outStateCostValue;

    public CompareSnip(CollegeInfo collegeInfo) {
        super(collegeInfo);

        this.sizeValue = new Text(collegeInfo.getSize() + "");
        //add(size);
        //add(sizeValue);

        this.actValue = new Text(collegeInfo.getACT() + "");
        if(actValue.getText() == "0"){
            actValue.setText("N/A");
        }
        //add(act);
        //add(actValue);

        this.inStateCostValue = new Text(collegeInfo.getIinStateCost() + "");
        //add(cost);
        //add(costValue);
        this.outStateCostValue = new Text(collegeInfo.getOutOfStateCost() + "");
        
        inStateCostLayout.add(inStateCost);
        inStateCostLayout.add(inStateCostValue);

        outStateCostLayout.add(outStateCost);
        outStateCostLayout.add(outStateCostValue);
        
        sizeLayout.add(size);
        sizeLayout.add(sizeValue);
        
        actLayout.add(act);
        actLayout.add(actValue);
        
        layout.add(inStateCostLayout);
        layout.add(outStateCostLayout);
        layout.add(sizeLayout);
        layout.add(actLayout);
        
    }
    public VerticalLayout getComparisonContent(){
        return this.layout;
    }
}

