package com.luni.data.service;

import com.luni.connection.FilterParams;
import com.luni.data.entity.CollegeInfo;
import com.luni.data.entity.Company;
import com.luni.data.entity.Contact;
import com.luni.data.entity.Status;
import com.luni.data.repository.CollegeRepository;
import com.luni.data.repository.CompanyRepository;
import com.luni.data.repository.ContactRepository;
import com.luni.data.repository.StatusRepository;
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

    public CrmService(){
        this.contactRepository = null;
        this.companyRepository = null;
        this.statusRepository = null;
        this.collegeRepository = null;
    }

    public CrmService(ContactRepository contactRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
        this.collegeRepository = new CollegeRepository();
    }

//    public List<Contact> findAllContacts(String stringFilter) {
//        if (stringFilter == null || stringFilter.isEmpty()) {
//            return contactRepository.findAll();
//        } else {
//           // return contactRepository.search(stringFilter);
//            return collegeRepository.search(stringFilter);
//        }
//    }

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
//        CollegeInfo collegeInfo = new CollegeInfo();
//        collegeInfo.setName("Rose-Hulman");
//        collegeInfo.setLocation("Terre Haute, IN");
//        collegeInfo.setURL("/images/rose.png");
//        list.add(collegeInfo);
//
//        CollegeInfo tu = new CollegeInfo();
//        tu.setName("University of Tulsa");
//        tu.setLocation("Tulsa, OK");
//        tu.setURL("/images/tu.jpg");
//        list.add(tu);
        return list;
    }

    public List<CollegeInfo> getCollegeInfosByName(String name, int maxResults){
        return verifyResults(FilterParams.searchName(name, maxResults));
    }
    public List<CollegeInfo> getCollegeInfosByLoc(String location){
        return verifyResults(FilterParams.searchCity(location));
    }
    public List<CollegeInfo> getCollegeInfosBySize(int min, int max){
        return verifyResults(FilterParams.searchSize(min, max));
    }
    public List<CollegeInfo> getCollegeInfosByCost(int min, int max, boolean inState){
        return verifyResults(FilterParams.searchCost(min, max, inState));
    }
    public List<CollegeInfo> getCollegeInfosByACT(int min, int max){
        return verifyResults(FilterParams.searchACT(min, max));
    }

    public List<CollegeInfo> verifyResults(List<CollegeInfo> list){
        // if no results were found, add one result to notify user none were found
        if(list.size() == 0){
            CollegeInfo collegeInfo = new CollegeInfo();
            collegeInfo.setURL("/images/no-results-found.png");
            collegeInfo.setName("No Results");
            collegeInfo.setLocation("Try adjusting filter parameters.");
            list.add(collegeInfo);
        }
        return list;
    }
}