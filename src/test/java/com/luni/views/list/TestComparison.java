package com.luni.views.list;

import com.luni.connection.ConnectionManager;
import com.luni.data.entity.CollegeInfo;
import com.luni.data.service.CrmService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestComparison {

    @Before
    public void init(){
        ConnectionManager.loadAPI_Key();
    }

    @Test
    public void testValidName(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("rose-hulman");
        col2.setNameValue("university of tulsa");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals("Rose-Hulman Institute of Technology", collegeInfo1.getName());
        Assert.assertEquals("University of Tulsa", collegeInfo2.getName());
    }

    @Test
    public void testLocationCompare(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("rose-hulman");
        col2.setNameValue("university of tulsa");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals("Terre Haute, IN", collegeInfo1.getLocation());
        Assert.assertEquals("Tulsa, OK", collegeInfo2.getLocation());
    }

    @Test
    public void testSizeCompare(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("rose-hulman");
        col2.setNameValue("university of tulsa");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals(1969, collegeInfo1.getSize());
        Assert.assertEquals(2905, collegeInfo2.getSize());
    }

    @Test
    public void testInStateCostCompare(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("rose-hulman");
        col2.setNameValue("university of tulsa");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals(52914, collegeInfo1.getInStateCost());
        Assert.assertEquals(43985, collegeInfo2.getInStateCost());
    }

    @Test
    public void testInStateCostComparePublicSchool(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("university of oklahoma");
        col2.setNameValue("university of alabama");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals(11688, collegeInfo1.getInStateCost());
        Assert.assertEquals(11338, collegeInfo2.getInStateCost());
    }

    @Test
    public void testOutOfStateCostCompare(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("rose-hulman");
        col2.setNameValue("university of tulsa");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals(52914, collegeInfo1.getOutOfStateCost());
        Assert.assertEquals(43985, collegeInfo2.getOutOfStateCost());
    }

    @Test
    public void testOutOfStateCostComparePublicSchool(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("university of oklahoma");
        col2.setNameValue("university of alabama");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals(27069, collegeInfo1.getOutOfStateCost());
        Assert.assertEquals(23734, collegeInfo2.getOutOfStateCost());
    }

    @Test
    public void testACTCompare(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("rose-hulman");
        col2.setNameValue("university of tulsa");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals(30, collegeInfo1.getACT());
        Assert.assertEquals(27, collegeInfo2.getACT());
    }

    @Test
    public void testInvalidSearchBarOne(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("zyxwv");
        col2.setNameValue("university of tulsa");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals("No Results", collegeInfo1.getName());
        Assert.assertEquals("University of Tulsa", collegeInfo2.getName());
    }

    @Test
    public void testInvalidSearchBarTwo(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("rose-hulman");
        col2.setNameValue("zyxwv");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals("Rose-Hulman Institute of Technology", collegeInfo1.getName());
        Assert.assertEquals("No Results", collegeInfo2.getName());
    }

    @Test
    public void testBothInvalidSearchBars(){
        CrmService service = new CrmService();
        ComparisonColumn col1 = new ComparisonColumn(service);
        ComparisonColumn col2 = new ComparisonColumn(service);

        col1.setNameValue("zyxwv");
        col2.setNameValue("zyxwv");

        col1.clickAddButton();
        col2.clickAddButton();

        CompareSnip compareSnip1 = col1.getCompareSnip();
        CompareSnip compareSnip2 = col2.getCompareSnip();

        Assert.assertNotNull(compareSnip1);
        Assert.assertNotNull(compareSnip2);

        CollegeInfo collegeInfo1 = compareSnip1.getCollegeInfo();
        CollegeInfo collegeInfo2 = compareSnip2.getCollegeInfo();

        Assert.assertEquals("No Results", collegeInfo1.getName());
        Assert.assertEquals("No Results", collegeInfo2.getName());
    }
}
