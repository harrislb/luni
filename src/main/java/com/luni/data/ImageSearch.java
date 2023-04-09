package com.luni.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.luni.connection.ConnectionManager;
import com.luni.connection.ResponseManager;

public class ImageSearch {

    private static Map<String, String>  imageMap = parseImageFile();

    private static final boolean USE_IMAGE_API = false;


    public static String getImage(String searchText) {
        String imageUrl = "";

        if(imageMap.get(searchText.toLowerCase()) != null){
            System.out.println("Found url for school : " + searchText);
            return imageMap.get(searchText.toLowerCase());
        }

        if(!USE_IMAGE_API){
            return imageUrl;
        }

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
            //  cache image
            imageMap.put(searchText.toLowerCase(), imageUrl);
            System.out.println(searchText.toLowerCase() + ", " + imageUrl);
            con.disconnect();

        }
        catch(Exception e){
            System.out.println("Error searching for image: " + searchText);
        }

        return imageUrl;
    }

    public static Map<String, String> parseImageFile(){
        Map<String, String> nameToURL = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("src\\test\\resources\\image-cache.txt"))){
            String line = reader.readLine();
            while(line != null){
                String[] vals = line.split(",[ ]");
                String name = vals[0];
                String url = vals[1];
                nameToURL.put(name, url);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameToURL;
    }


}
