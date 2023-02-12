import com.luni.connection.ConnectionManager;
import com.luni.connection.ResponseManager;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class TestParseJSON {

    @Test
    public void testJSON() throws IOException, JSONException {
        ConnectionManager.loadAPI_Key();
        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        Map<String, String> params = new HashMap<>();
        params.put("school.name", "rose-hulman");
        URL url = new URL(ConnectionManager.formatURL(baseURL, params));

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.connect();

        Map<String, Object> map = ResponseManager.parseJson(con);



        // TODO remove print statements later, used for testing
        System.out.println("keys: " + map.keySet());
        System.out.println( ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("city"));
        System.out.println( ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("state"));
        Assert.assertEquals("Terre Haute", ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("city"));

    }

    @Test
    public void testMultipleResults() throws IOException, JSONException {
        ConnectionManager.loadAPI_Key();
        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        Map<String, String> params = new HashMap<>();
        params.put("school.name", "boston");
        URL url = new URL(ConnectionManager.formatURL(baseURL, params));

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.connect();

        Map<String, Object> map = ResponseManager.parseJson(con);

        int numResults = ((ArrayList<HashMap>)map.get("results")).size();
        Assert.assertEquals(10, numResults);

        Assert.assertEquals("MA", ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("state"));
        Assert.assertEquals("Boston", ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("city"));


        Assert.assertEquals("MA", ((HashMap)((HashMap)((List)map.get("results")).get(9)).get("school")).get("state"));
        Assert.assertEquals("Boston", ((HashMap)((HashMap)((List)map.get("results")).get(9)).get("school")).get("city"));
    }
}
