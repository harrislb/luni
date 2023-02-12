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



            Map<String, Object> map =  ResponseManager.parseJson(con);
            int numResults = ((ArrayList<HashMap>)map.get("results")).size();

            for(int i = 0; i < numResults; i++){
                String actualName = ((HashMap)((HashMap)((List)map.get("results")).get(i)).get("school")).get("name").toString();
                String city = ((HashMap)((HashMap)((List)map.get("results")).get(i)).get("school")).get("city").toString();
                String state = ((HashMap)((HashMap)((List)map.get("results")).get(i)).get("school")).get("state").toString();
                CollegeInfo collegeInfo = new CollegeInfo();
                collegeInfo.setName(actualName);
                collegeInfo.setLocation(city + ", " + state);
                matches.add(collegeInfo);
            }
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public static List<CollegeInfo> searchCity(String searchCity){
        Map<String, String> params = new HashMap<>();
        // see if they included a state
        if(searchCity.contains(", ")){
            int commaIndex = searchCity.indexOf(",") + 1;
            if(searchCity.length() > commaIndex + 1){
                String state = searchCity.substring(commaIndex + 1);
                params.put("school.state", state);
            }
        }
        // remove any space from name to format for URL
        searchCity = searchCity.replace(' ', '-');
        List<CollegeInfo> matches = new ArrayList<>();

        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        params.put("school.city", searchCity);
        URL url = null;
        try {
            url = new URL(ConnectionManager.formatURL(baseURL, params));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.connect();



            Map<String, Object> map =  ResponseManager.parseJson(con);
            int numResults = ((ArrayList<HashMap>)map.get("results")).size();

            for(int i = 0; i < numResults; i++){
                String name = ((HashMap)((HashMap)((List)map.get("results")).get(i)).get("school")).get("name").toString();
                String city = ((HashMap)((HashMap)((List)map.get("results")).get(i)).get("school")).get("city").toString();
                String state = ((HashMap)((HashMap)((List)map.get("results")).get(i)).get("school")).get("state").toString();
                CollegeInfo collegeInfo = new CollegeInfo();
                collegeInfo.setName(name);
                collegeInfo.setLocation(city + ", " + state);
                matches.add(collegeInfo);
            }
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }
}
