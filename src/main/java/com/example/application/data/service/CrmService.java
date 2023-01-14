package com.example.application.data.service;

import com.example.application.data.entity.CollegeInfo;
import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.CollegeRepository;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrmService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;
    private final CollegeRepository collegeRepository;

    private static final String REQUEST_ROOT = "";

    public CrmService(ContactRepository contactRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
        this.collegeRepository = new CollegeRepository();
    }

    public List<Contact> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        } else {
           // return contactRepository.search(stringFilter);
            return collegeRepository.search(stringFilter);
        }
    }

    public long countContacts() {
        return contactRepository.count();
    }

    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    public void saveContact(Contact contact) {
        if (contact == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        contactRepository.save(contact);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }

    public void urlRequest(){
        URL url = null;
        try {
            url = new URL("/v1/api/college-list");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<CollegeInfo> getCollegeInfos(){
        List<CollegeInfo> list = new ArrayList<>();
        CollegeInfo collegeInfo = new CollegeInfo();
        collegeInfo.setName("Rose-Hulman");
        collegeInfo.setLocation("Terre Haute, IN");
        collegeInfo.setURL("images/rose.png");
        list.add(collegeInfo);

        CollegeInfo tu = new CollegeInfo();
        tu.setName("University of Tulsa");
        tu.setLocation("Tulsa, OK");
        tu.setURL("images/tu.jpg");
        list.add(tu);
        return list;
    }
}