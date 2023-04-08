import com.luni.connection.ConnectionManager;
import com.luni.data.service.PythonExecutor;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestPython {

    @Test
    public void callPythonFromJava() throws Exception {

        try {

            Process p = PythonExecutor.executePythonProcess("src/test/resources/hello.py", 0);

            if(p == null){
                Assert.fail();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            Assert.assertEquals("Hello World!", ret);
        }

        catch(Exception e){System.out.println(e);}
    }

    @Test
    public void printPythonArgsFromJava() throws Exception {

        try {
            int number1 = 10;

            Process p = PythonExecutor.executePythonProcess("src/test/resources/test2.py", number1);

            if(p == null){
                Assert.fail();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            Assert.assertEquals("Param 1: 10", ret);
        }

        catch(Exception e){System.out.println(e);}
    }
}
