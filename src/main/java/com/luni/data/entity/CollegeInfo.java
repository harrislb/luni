package com.luni.data.entity;

public class CollegeInfo {

    private String name;
    private String location;
    private int size;
    private int act;
    private int outOfStateCost;
    private int inStateCost;
    // TODO create a default placeholder image
    private String url = "/images/rose.png";

    public CollegeInfo(){

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return this.location;
    }

    public void setURL(String url){
        this.url = url;
    }

    public String getURL(){
        return this.url;
    }

    public int getACT(){
        return this.act;
    }

    public void setACT(int act){
        this.act = act;
    }

    public int getSize(){
        return this.size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public int getOutOfStateCost(){
        return this.outOfStateCost;
    }

    public void setOutOfStateCost(int outOfStateCost){
        this.outOfStateCost = outOfStateCost;
    }

    public int getIinStateCost(){
        return this.inStateCost;
    }

    public void setInStateCost(int inStateCost){
        this.inStateCost = inStateCost;
    }
}
