import com.luni.connection.ConnectionManager;
import org.junit.Assert;
import org.junit.Test;


public class TestReadAPIKey {

    @Test
    public void testAPIKey(){
        String key = ConnectionManager.loadAPI_Key();
        Assert.assertNotEquals(null, key);
        Assert.assertNotEquals("", key);
        Assert.assertNotEquals(" ", key);
        Assert.assertEquals(key, ConnectionManager.API_KEY);
    }
}
