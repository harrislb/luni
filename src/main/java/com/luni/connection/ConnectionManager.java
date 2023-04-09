package com.luni.connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

public class ConnectionManager {
	public static String secretkey;

    public static String API_KEY = "";
    public static String IMAGE_API_KEY = "";
    public static String CX_KEY = "";

    public static String loadAPI_Key(){
        BufferedReader reader;
        BufferedReader imageReader;
        BufferedReader cxReader;

        try {
            // TODO will need to implement this on the server side so we can run once we have CICD running.
            // store the API key in a local file for now; do not publish to repo
            //System.out.println("THIS IS THE KEY!!!!!!! " + ConnectionManager.getAPIKey());
        	reader = new BufferedReader(new FileReader("./api-key.txt"));
            String line = reader.readLine();
            reader.close();
            // set the API Key
            API_KEY = line;
            System.out.println("line: " + line);
            //line = ConnectionManager.getAPIKey();

//            // set the image API Key
//            IMAGE_API_KEY = imageReader.readLine();
//            imageReader.close();
//
//            // set the cx API key
//            CX_KEY = cxReader.readLine();
//            cxReader.close();


            return line;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Formats the given base URL with the parameters and API_KEY. Warning: must load api key prior to calling this method.
     * @param baseURL the base URL to add parameters
     * @param parameters map of parameter name to parameter value
     * @return formatted URL with API_KEY and given parameters.
     */
    public static String formatURL(String baseURL, Map<String, String> parameters){
        StringBuilder sb = new StringBuilder(baseURL);
        sb.append("?");
        sb.append("api_key=");
        sb.append(API_KEY);

        if(parameters != null && !parameters.isEmpty()){
            for(Map.Entry<String, String> entry : parameters.entrySet()){
                sb.append("&");
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }
    
    public static void setApiKey(String key) {
    	ConnectionManager.secretkey = key;
    }
    
    public static String getAPIKey() {
    	return ConnectionManager.secretkey;
    }
}
