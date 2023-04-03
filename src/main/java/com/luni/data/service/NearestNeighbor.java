package com.luni.data.service;

import com.luni.connection.ConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NearestNeighbor {

    public static String retrieveNearestNeighbors(String cost){
        try{
            int costInt = Integer.parseInt(cost);

            Process p = PythonExecutor.executePythonProcess("src\\test\\resources\\testknn.py", ConnectionManager.API_KEY, costInt);

            if(p == null){
                return "Unable to recommend schools.";
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder ret = new StringBuilder(in.readLine());

            String value = ret.toString();

            while(value != null){
                System.out.println(value);
                value = in.readLine();
                ret.append(value);
            }

            return ret.toString();
        }
        catch(NumberFormatException e){
            return "Invalid Cost. No Matches Found.";
        } catch (IOException e) {
            return "Error parsing output of recommendations";
        }
    }

}
