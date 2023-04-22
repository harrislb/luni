package com.luni.data.service;

import com.luni.connection.FilterParams;
import com.luni.data.entity.CollegeInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrmService {


    private static final String REQUEST_ROOT = "";

    public CrmService() {
    }

    public List<CollegeInfo> getCollegeInfosByName(String name, int maxResults){
        return verifyResults(FilterParams.searchName(name, maxResults));
    }
    public List<CollegeInfo> getCollegeInfosByLoc(String location){
        return verifyResults(FilterParams.searchCity(location));
    }
    public List<CollegeInfo> getCollegeInfosBySize(int min, int max){
        return verifyResults(FilterParams.searchSize(min, max));
    }
    public List<CollegeInfo> getCollegeInfosByCost(int min, int max, boolean inState){
        return verifyResults(FilterParams.searchCost(min, max, inState));
    }
    public List<CollegeInfo> getCollegeInfosByACT(int min, int max){
        return verifyResults(FilterParams.searchACT(min, max));
    }

    public CollegeInfo retrieveCollege(String name){
        List<CollegeInfo> colleges = FilterParams.searchName(name, 1);
        if(colleges.size() > 0){
            return colleges.get(0);
        }
        return null;
    }

    public List<CollegeInfo> verifyResults(List<CollegeInfo> list){
        // if no results were found, add one result to notify user none were found
        if(list.size() == 0){
            CollegeInfo collegeInfo = new CollegeInfo();
            collegeInfo.setURL("/images/no-results-found.png");
            collegeInfo.setName("No Results");
            collegeInfo.setLocation("Try adjusting filter parameters.");
            list.add(collegeInfo);
        }
        return list;
    }
}