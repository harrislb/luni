package com.luni.views.list;

import com.luni.connection.ConnectionManager;
import com.luni.connection.FilterParams;
import com.luni.data.entity.CollegeInfo;
import com.luni.data.service.CrmService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestService {

    private CrmService service;

    @Before
    public void init(){
        ConnectionManager.loadAPI_Key();
        this.service = new CrmService();
    }

    @Test
    public void testNameService(){
        List<CollegeInfo> collegeInfoList = service.getCollegeInfosByName("rose-hulman", -1);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertEquals("Terre Haute, IN", collegeInfoList.get(0).getLocation());
    }



    @Test
    public void testLocationService(){
        List<CollegeInfo> collegeInfoList = service.getCollegeInfosByLoc("Franklin, TN");
        Assert.assertNotNull(collegeInfoList);
        Assert.assertEquals("Franklin, TN", collegeInfoList.get(0).getLocation());
    }


    @Test
    public void testSizeService(){
        List<CollegeInfo> collegeInfoList = service.getCollegeInfosBySize(1000, 4999);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        int size = collegeInfoList.get(0).getSize();
        boolean isValid = size < 5000 && size > 999;
        Assert.assertTrue(isValid);
    }


    @Test
    public void testCostService(){
        // out of state cost
        List<CollegeInfo> collegeInfoList = service.getCollegeInfosByCost(5000, 14999, false);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        int cost = collegeInfoList.get(0).getOutOfStateCost();
        boolean isValid = cost < 14999 && cost > 4999;
        Assert.assertTrue(isValid);

        collegeInfoList = FilterParams.searchCost(5000, 14999, false);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        // in state cost
        cost = collegeInfoList.get(0).getInStateCost();
        isValid = cost < 14999 && cost > 4999;
        Assert.assertTrue(isValid);
    }

    @Test
    public void testACTService(){
        List<CollegeInfo> collegeInfoList = service.getCollegeInfosByACT(20, 25);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        int act = collegeInfoList.get(0).getACT();
        boolean isValid = act < 26 && act > 19;
        Assert.assertTrue(isValid);
    }
}
