package com.example.application.data.repository;

import com.example.application.data.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class CollegeRepository {

    public CollegeRepository(){

    }
    public List<Contact> search(String stringFilter) {
        List<Contact> colleges = new ArrayList<>();
        Contact c1 = new Contact();
        Contact c2 = new Contact();
        Contact c3 = new Contact();

        colleges.add(c1);
        colleges.add(c2);
        colleges.add(c3);

        int i = 1;
        for(Contact c : colleges){
            c.setFirstName("Test college Name: " + i);
            c.setLastName("Test college city: " + i);
            c.setEmail("test email @ " + i);
        }

        return colleges;
    }
}
