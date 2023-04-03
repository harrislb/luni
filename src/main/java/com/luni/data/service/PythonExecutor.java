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
}
