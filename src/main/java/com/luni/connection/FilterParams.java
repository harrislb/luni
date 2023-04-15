package com.luni.connection;

import com.luni.data.ImageSearch;
import com.luni.data.entity.CollegeInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterParams {

    public static int MAX_RESULTS = 8;

    public static List<CollegeInfo> searchName(String name, int maxResults){
        if(maxResults == -1){
            maxResults = MAX_RESULTS;
        }
        // remove any space from name to format for URL
        name = name.replace(' ', '-');
        List<CollegeInfo> matches = new ArrayList<>();

        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        Map<String, String> params = new HashMap<>();
        params.put("school.name", name);
        matches = queryCollegeInfos(baseURL, params, maxResults);

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
            searchCity = searchCity.substring(0, commaIndex - 1);
        }
        // remove any space from name to format for URL
        searchCity = searchCity.replace(' ', '-');
        List<CollegeInfo> matches = new ArrayList<>();

        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        params.put("school.city", searchCity);
        matches = queryCollegeInfos(baseURL, params, MAX_RESULTS);

        return matches;
    }

    public static List<CollegeInfo> searchSize(int min, int max){
        List<CollegeInfo> matches = new ArrayList<>();

        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        Map<String, String> params = new HashMap<>();
        String rangeString = min + ".." + max;
        params.put("latest.student.size__range", rangeString);

        matches = queryCollegeInfos(baseURL, params, MAX_RESULTS);

        return matches;
    }

    public static List<CollegeInfo> searchCost(int min, int max, boolean inState){
        List<CollegeInfo> matches = new ArrayList<>();

        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        Map<String, String> params = new HashMap<>();
        String rangeString = min + ".." + max;
        if(inState){
            params.put("cost.tuition.in_state__range", rangeString);

        }
        else{
            params.put("cost.tuition.out_of_state__range", rangeString);
        }
        matches = queryCollegeInfos(baseURL, params, MAX_RESULTS);

        return matches;
    }

    public static List<CollegeInfo> searchACT( int min, int max){
        List<CollegeInfo> matches = new ArrayList<>();

        String baseURL = "https://api.data.gov/ed/collegescorecard/v1/schools";
        Map<String, String> params = new HashMap<>();
        String rangeString = min + ".." + max;
        params.put("admissions.act_scores.midpoint.cumulative", rangeString);

        matches = queryCollegeInfos(baseURL, params, MAX_RESULTS);

        return matches;
    }

    public static List<CollegeInfo> queryCollegeInfos(String baseURL, Map<String, String> params, int maxResults){
        List<CollegeInfo> matches = new ArrayList<>();
        URL url = null;
        try {
            url = new URL(ConnectionManager.formatURL(baseURL, params));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.connect();

            Map<String, Object> map =  ResponseManager.parseJson(con);

            int numResults;
            if(((ArrayList<HashMap>)map.get("results")) != null){
                numResults = ((ArrayList<HashMap>)map.get("results")).size();
            }
            else{
                numResults = 0;
            }

            //TODO manage # of queries
            if(numResults > maxResults){
                numResults = maxResults;
            }

            for(int i = 0; i < numResults; i++){
                String name = ((HashMap)((HashMap)((List)map.get("results")).get(i)).get("school")).get("name").toString();
                String city = ((HashMap)((HashMap)((List)map.get("results")).get(i)).get("school")).get("city").toString();
                String state = ((HashMap)((HashMap)((List)map.get("results")).get(i)).get("school")).get("state").toString();
                CollegeInfo collegeInfo = new CollegeInfo();
                collegeInfo.setName(name);
                collegeInfo.setLocation(city + ", " + state);
                // TODO use this for image search
                collegeInfo.setURL(ImageSearch.getImage(name + " main"));

//                if(collegeInfo.getURL().contains("lookaside") || collegeInfo.getURL().equals("")){
//                    continue;
//                }

                try{
                    int outOfStateCost = (int) ((HashMap)((HashMap)((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("latest")).get("cost")).get("tuition")).get("out_of_state");
                    collegeInfo.setOutOfStateCost(outOfStateCost);
                    int inStateCost = (int) ((HashMap)((HashMap)((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("latest")).get("cost")).get("tuition")).get("in_state");
                    collegeInfo.setInStateCost(inStateCost);

                    int size = (int)((HashMap)((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("latest")).get("student")).get("size");
                    collegeInfo.setSize(size);

                    // act score can be null
                    Object act = ((HashMap)((HashMap)((HashMap)((HashMap) ((HashMap) ((List) map.get("results")).get(0)).get("latest")).get("admissions")).get("act_scores")).get("midpoint")).get("cumulative");
                    try{
                        int actValue = (int)act;
                        collegeInfo.setACT(actValue);
                    }
                    catch(Exception e){
                        // no cumulative data available
                    }
                }
                catch(Exception e){
                    System.out.println("Error populating additional fields for results.");
                }

                matches.add(collegeInfo);
            }
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }
}
