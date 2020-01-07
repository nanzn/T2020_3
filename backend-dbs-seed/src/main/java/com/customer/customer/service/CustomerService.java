package com.customer.customer.service;

import com.customer.customer.dao.CustomerDAO;
import com.customer.customer.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    private List<Customer> customerList = new CopyOnWriteArrayList<>();

    RestTemplate restTemplate = new RestTemplate();

    public List<Customer> getAllCustomers(){
        return customerDAO.findAll();
    }

    public boolean isExistedCustomer(int customerId){
        customerList = this.getAllCustomers();
        String id = Integer.toString(customerId);
        return customerList.contains(id);
    }

    public String getCustomerName(String username){
        customerList = this.getAllCustomers();
        Customer reqCust = customerList.stream().filter(m->m.getUsername().equals( username)).findFirst().get();
        if(reqCust.getGender().equals("Male")){
            return "Mr. "+ reqCust.getLastName();
        }else{
            return "Ms. " + reqCust.getLastName();
        }

    }

    public String getRiskLevel(String username){
        customerList = this.getAllCustomers();
        Customer reqCust = customerList.stream().filter(m->m.getUsername().equals( username)).findFirst().get();
        return reqCust.getRiskLevel();
    }

    public String getSelectedMember(String username, String password ){

        customerList  = this.getAllCustomers();
        try{
            Customer reqCust =  customerList.stream().filter(m->m.getUsername().equals(username)).findFirst().get();
            if(password.equals(reqCust.getPassword()) == false){
                return "Invalid Username/Password";
            }else{
                return "Login Successful";
            }
        }catch(NoSuchElementException e){
            return "Invalid Username/Password";
        }

    }


//    public String saveCustomer(int customerId){
//        String finalResult = "";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("Identity","T49");
//        headers.add("Token", "bad9e5d0-1800-4c8e-9286-b96453f75809");
//        HttpEntity<String> entity  = new HttpEntity<String>(headers);
//        String hyperlink = "http://techtrek-api-gateway.ap-southeast-1.elasticbeanstalk.com/customers/"+customerId+"/details";
//        String result =restTemplate.exchange(hyperlink, HttpMethod.GET,entity,String.class).getBody();
//        ObjectMapper mapper = new ObjectMapper();
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        try{
//            map = mapper.readValue(result, new TypeReference<Map<String, Object>>(){});
//            if(this.isExistedCustomer(customerId)){
//                return "Existed customer";
//            }else{
//                try {
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//                    Date parsedLogin = dateFormat.parse(map.get("lastLogIn").toString());
//                    Timestamp timestampLogin = new java.sql.Timestamp(parsedLogin.getTime());
//
//                    Date parsedDoe = dateFormat.parse(map.get("dateOfBirth").toString());
//                    Timestamp timestampDoe = new java.sql.Timestamp(parsedDoe.getTime());
//
//
//
//                    Customer cust = new Customer(map.get("customerId").toString(),
//                            map.get("gender").toString(),
//                            map.get("firstName").toString(),
//                            map.get("lastName").toString(),
//                            timestampLogin,
//                            timestampDoe,
//                            map.get("riskLevel").toString());
//                    customerDAO.save(cust);
//                    finalResult = "record successful";
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//        }catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return finalResult;
//    }


}
