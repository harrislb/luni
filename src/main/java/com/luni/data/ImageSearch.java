package com.luni.data;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.luni.connection.ConnectionManager;
import com.luni.connection.ResponseManager;

public class ImageSearch {


    public static String getImage(String searchText) {
        String imageUrl = "";

        try{
            // Replace with your own API key and search engine ID
            String apiKey = ConnectionManager.IMAGE_API_KEY;
            String cx = ConnectionManager.CX_KEY;

            // Encode search text for URL query
            String query = URLEncoder.encode(searchText, "UTF-8");

            // Construct URL for Google Custom Search JSON API query
            String url = "https://www.googleapis.com/customsearch/v1?key=" + apiKey + "&cx=" + cx + "&q=" + query + "&searchType=image&num=1";

            // Send HTTP GET request to API endpoint
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            Map<String, Object> map =  ResponseManager.parseJson(con);

            imageUrl = ((String)((HashMap)((List)map.get("items")).get(0)).get("link"));
            con.disconnect();

        }
        catch(Exception e){
            System.out.println("Error searching for image: " + searchText);
        }

        return imageUrl;
    }


}
