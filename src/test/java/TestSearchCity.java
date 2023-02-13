import com.luni.connection.ConnectionManager;
import com.luni.connection.ResponseManager;
import org.h2.util.json.JSONNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSearchCity {

    @Test
    public void testSearchCity() throws IOException, JSONException {
        ConnectionManager.loadAPI_Key();
        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        Map<String, String> params = new HashMap<>();
        params.put("school.city", "Boston");
        URL url = new URL(ConnectionManager.formatURL(baseURL, params));

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.connect();

        Map<String, Object> map = ResponseManager.parseJson(con);


        // TODO remove print statements later, used for testing
        System.out.println("keys: " + map.keySet());
        System.out.println(((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("school")).get("city"));
        System.out.println(((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("school")).get("state"));
        Assert.assertEquals("Boston", ((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("school")).get("city"));

        int outOfStateCost = (int) ((HashMap)((HashMap)((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("latest")).get("cost")).get("tuition")).get("out_of_state");
        Assert.assertEquals(12600, outOfStateCost);

        int size = (int)((HashMap)((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("latest")).get("student")).get("size");
        Assert.assertEquals(47, size);



        // act score can be null
        Object act = ((HashMap)((HashMap)((HashMap)((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("latest")).get("admissions")).get("act_scores")).get("midpoint")).get("cumulative");
        try{
            int actValue = (int)act;
        }
        catch(Exception e){
            // no cumulative data available
        }
    }
}
