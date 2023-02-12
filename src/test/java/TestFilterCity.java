import com.luni.connection.ConnectionManager;
import com.luni.connection.ResponseManager;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFilterCity {

    @Test
    public void testSearchCity() throws IOException, JSONException {
        ConnectionManager.loadAPI_Key();
        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        Map<String, String> params = new HashMap<>();
        params.put("school.city", "tulsa");
        URL url = new URL(ConnectionManager.formatURL(baseURL, params));

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.connect();

        Map<String, Object> map = ResponseManager.parseJson(con);


        // TODO remove print statements later, used for testing
        System.out.println("keys: " + map.keySet());
        System.out.println(((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("school")).get("city"));
        System.out.println(((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("school")).get("state"));
        Assert.assertEquals("Tulsa", ((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("school")).get("city"));
    }
}
