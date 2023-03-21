package com.luni;

import com.luni.connection.ConnectionManager;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "flowcrmtutorial")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
   
        for (String arg : args) {

            System.out.println(arg);

        }
        System.out.println("arg 0: " + args[0]);
        String[] key = args[0].split("=");
        System.out.println("key: " + key[1]);
        //String skey = "TESTING";
        

        try {
            java.io.InputStream is = Application.class.getResourceAsStream("pom.xml");
            java.util.Properties p = new Properties();
			p.load(is);
	        String skey = p.getProperty("skey");
	        ConnectionManager.setApiKey(skey);
	        System.out.println("connection key::: " + ConnectionManager.getAPIKey());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
        
        SpringApplication.run(Application.class, args);
    }
    
}
