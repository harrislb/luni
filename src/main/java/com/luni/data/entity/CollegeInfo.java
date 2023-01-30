package com.luni.data.entity;

public class CollegeInfo {

    private String name;
    private String location;
    private String url;

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
}
