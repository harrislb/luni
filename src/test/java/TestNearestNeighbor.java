import com.luni.connection.ConnectionManager;
import com.luni.data.service.NearestNeighbor;
import org.junit.Assert;
import org.junit.Test;

public class TestNearestNeighbor {

    @Test
    public void testInvalidCost(){
        ConnectionManager.loadAPI_Key();

        String neighbors = NearestNeighbor.retrieveNearestNeighbors("zxy");
        Assert.assertEquals("Invalid Cost. No Matches Found.", neighbors );

    }

    @Test
    public void testValidCost(){
        ConnectionManager.loadAPI_Key();

        String neighbors = NearestNeighbor.retrieveNearestNeighbors("10000");
        String expected = "['Texas College', 'Rust College', 'Paul Quinn College', 'Rabbinical Seminary of America']";
        Assert.assertEquals(expected, neighbors );

    }

    @Test
    public void test6000Cost(){
        ConnectionManager.loadAPI_Key();

        String neighbors = NearestNeighbor.retrieveNearestNeighbors("6000");
        String expected = "['Northeast Iowa Community College', 'Minnesota West Community and Technical College', 'Dakota County Technical College', 'Southeastern Community College']";
        Assert.assertEquals(expected, neighbors );
    }

    @Test
    public void test20000Cost(){
        ConnectionManager.loadAPI_Key();

        String neighbors = NearestNeighbor.retrieveNearestNeighbors("22000");
        String expected = "['Dorsey College-Dearborn', 'Boston Architectural College', 'Spartan College of Aeronautics and Technology', 'Truett McConnell University']";
        Assert.assertEquals(expected, neighbors );
    }

}
