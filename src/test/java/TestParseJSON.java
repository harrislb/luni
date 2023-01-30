import com.luni.connection.ConnectionManager;
import com.luni.connection.ResponseManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        JSONObject jsonObject = new JSONObject(sb.toString()) ;
        Map<String, Object> map =  ResponseManager.jsonToMap(jsonObject) ;
        // TODO remove print statements later, used for testing
        System.out.println("keys: " + map.keySet());
        System.out.println( ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("city"));
        System.out.println( ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("state"));
        Assert.assertEquals("Terre Haute", ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("city"));

    }
}
