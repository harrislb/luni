import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestPython {

    @Test
    public void printPythonFromJava() throws Exception {

        try {
            int number1 = 10;
            int number2 = 32;

            ProcessBuilder pb = new ProcessBuilder("python","src\\test\\resources\\hello.py","" + number1,"" + number2);
            pb.redirectErrorStream(true);
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            Assert.assertEquals("Hello World!", ret);
        }

        catch(Exception e){System.out.println(e);}
    }



}
