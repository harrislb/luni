package com.luni.data.service;

public class PythonExecutor {

    public static Process executePythonProcess(String filePath, int arg){
        try {
            ProcessBuilder pb = new ProcessBuilder("python", filePath,"" + arg);
            pb.redirectErrorStream(true);
            Process p = pb.start();

           return p;
        }

        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static Process executePythonProcess(String filePath, String apiKey, int cost){
        try {
            ProcessBuilder pb = new ProcessBuilder("python", filePath, apiKey, "" + cost);
            pb.redirectErrorStream(true);
            return pb.start();
        }

        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}

