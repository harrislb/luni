import com.luni.connection.ConnectionManager;
import com.luni.connection.FilterParams;
import com.luni.data.entity.CollegeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestFilter {

    @Before
    public void init(){
        ConnectionManager.loadAPI_Key();
    }

    @Test
    public void testValidName(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchName("rose-hulman", -1);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertEquals("Terre Haute, IN", collegeInfoList.get(0).getLocation());
    }

    @Test
    public void testInvalidName(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchName("zyxwv", -1);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertEquals(0, collegeInfoList.size());
    }

    @Test
    public void testMultipleNameResults(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchName("rose", -1);
        // verify we get results
        Assert.assertNotNull(collegeInfoList);
        // verify we have more than 1 result
        Assert.assertNotEquals(0, collegeInfoList.size());
        Assert.assertNotEquals(1, collegeInfoList.size());
        // verify the results are not the same school
        Assert.assertNotEquals(collegeInfoList.get(0).getName(), collegeInfoList.get(1).getName());
    }

    @Test
    public void testValidCity(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchCity("Franklin, TN");
        Assert.assertNotNull(collegeInfoList);
        Assert.assertEquals("Franklin, TN", collegeInfoList.get(0).getLocation());
    }

    @Test
    public void testInvalidCity(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchCity("zyxwv");
        Assert.assertNotNull(collegeInfoList);
        Assert.assertEquals(0, collegeInfoList.size());
    }

    @Test
    public void testMultipleCityResults(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchCity("Franklin, TN");
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());
        Assert.assertNotEquals(1, collegeInfoList.size());

        for(CollegeInfo collegeInfo : collegeInfoList){
            // many states have 'franklin', ensure these are all franklin, tn
            Assert.assertEquals("Franklin, TN", collegeInfo.getLocation());
        }
    }


    @Test
    public void testSingleValidSize(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchSize(1000, 4999);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        int size = collegeInfoList.get(0).getSize();
        boolean isValid = size < 5000 && size > 999;
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidSize() {
        List<CollegeInfo> collegeInfoList = FilterParams.searchSize(0, 0);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertEquals(0, collegeInfoList.size());
    }

    @Test
    public void testMultipleValidSize(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchSize(1000, 4999);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        for(CollegeInfo collegeInfo : collegeInfoList){
            int size = collegeInfo.getSize();
            boolean isValid = size < 5000 && size > 999;
            Assert.assertTrue(isValid);
        }
    }

    @Test
    public void testSingleValidCost(){
        // out of state cost
        List<CollegeInfo> collegeInfoList = FilterParams.searchCost(5000, 14999, false);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        int cost = collegeInfoList.get(0).getOutOfStateCost();
        boolean isValid = cost < 14999 && cost > 4999;
        Assert.assertTrue(isValid);

        collegeInfoList = FilterParams.searchCost(5000, 14999, false);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        // in state cost
        cost = collegeInfoList.get(0).getIinStateCost();
        isValid = cost < 14999 && cost > 4999;
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidCost(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchCost(0, 0, false);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertEquals(0, collegeInfoList.size());
    }

    @Test
    public void testMultipleCost(){
        // test out-of-state cost
        List<CollegeInfo> collegeInfoList = FilterParams.searchCost(5000, 14999, false);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        int cost = collegeInfoList.get(0).getOutOfStateCost();
        boolean isValid = cost < 14999 && cost > 4999;
        Assert.assertTrue(isValid);

        // test in-state cost
        collegeInfoList = FilterParams.searchCost(5000, 14999, true);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        cost = collegeInfoList.get(0).getIinStateCost();
        isValid = cost < 14999 && cost > 4999;
        Assert.assertTrue(isValid);
    }

    @Test
    public void testSingleValidACT(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchACT(20, 25);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        int act = collegeInfoList.get(0).getACT();
        boolean isValid = act < 26 && act > 19;
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidACT(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchACT(0, 2);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertEquals(0, collegeInfoList.size());
    }

    @Test
    public void testMultipleValidACT(){
        List<CollegeInfo> collegeInfoList = FilterParams.searchACT(30, 36);
        Assert.assertNotNull(collegeInfoList);
        Assert.assertNotEquals(0, collegeInfoList.size());

        for(CollegeInfo collegeInfo : collegeInfoList){
            int act = collegeInfo.getACT();
            boolean isValid = act < 37 && act > 29;
            Assert.assertTrue(isValid);
        }
    }

}
