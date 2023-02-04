package com.luni.connection;

import com.luni.data.entity.CollegeInfo;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterName {

    public static List<CollegeInfo> searchName(String name){
        // remove any space from name to format for URL
        name = name.replace(' ', '-');
        List<CollegeInfo> matches = new ArrayList<>();

        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        Map<String, String> params = new HashMap<>();
        params.put("school.name", name);
        URL url = null;
        try {
            url = new URL(ConnectionManager.formatURL(baseURL, params));
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
            String actualName = ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("name").toString();
            String city = ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("city").toString();
            String state = ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("state").toString();
            System.out.println( ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("city"));
            System.out.println( ((HashMap)((HashMap)((List)map.get("results")).get(0)).get("school")).get("state"));

            CollegeInfo collegeInfo = new CollegeInfo();
            collegeInfo.setName(actualName);
            collegeInfo.setLocation(city + ", " + state);
            matches.add(collegeInfo);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return matches;
    }


}
