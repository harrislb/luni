import com.luni.connection.ConnectionManager;
import com.luni.connection.ResponseManager;
import org.junit.Assert;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestQuery {

    @Test
    public void testNameQuery(){
        ConnectionManager.loadAPI_Key();
        try{
            String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
            Map<String, String> params = new HashMap<>();
            params.put("school.name", "harvey");
            URL url = new URL(ConnectionManager.formatURL(baseURL, params));
            System.out.println(url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.connect();


            int status = con.getResponseCode();
            String message = con.getResponseMessage();
            Assert.assertEquals(200, status);
            Assert.assertEquals("OK", message);


            Map<String, Object> map = ResponseManager.parseJson(con);


            // TODO remove print statements later, used for testing
            System.out.println("keys: " + map.keySet());
            System.out.println(((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("school")).get("city"));
            System.out.println(((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("school")).get("state"));
            Assert.assertEquals("Claremont", ((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("school")).get("city"));


        }
        catch(Exception e){
            Assert.fail("Exception in test name query.");
        }


    }
}
