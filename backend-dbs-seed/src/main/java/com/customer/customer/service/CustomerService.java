package com.customer.customer.service;

import com.customer.customer.dao.CustomerDAO;
import com.customer.customer.model.Customer;
import com.fasterxml.jackson.core.JsonParseException;
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
        try{
            Customer reqCust = customerList.stream().filter(m->m.getUsername().equals( username)).findFirst().get();
            if(reqCust.getGender().equals("Male")){
                return "Mr. "+ reqCust.getLastName();
            }else{
                return "Ms. " + reqCust.getLastName();
            }

        }catch(NoSuchElementException e){
            return null;
        }

    }

    public String getRiskLevel(String username){
        customerList = this.getAllCustomers();
        try{
            Customer reqCust = customerList.stream().filter(m->m.getUsername().equals( username)).findFirst().get();
            return reqCust.getRiskLevel();
        }catch(NoSuchElementException e) {
            return null;
        }
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

    public String getCustomerId(String username){
        customerList = this.getAllCustomers();
        try{
            Customer reqCust = customerList.stream().filter(m->m.getUsername().equals( username)).findFirst().get();
            return reqCust.getCustomerId();
        }catch(NoSuchElementException e ){
            return null;
        }

    }

    public int getAccountId(String username){
        int finalResult = 0;
        String customerId = this.getCustomerId(username);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Identity","T49");
        headers.add("Token", "bad9e5d0-1800-4c8e-9286-b96453f75809");
        HttpEntity<String> entity  = new HttpEntity<String>(headers);
        String hyperlink = "http://techtrek-api-gateway.ap-southeast-1.elasticbeanstalk.com/accounts/deposit/"+customerId;
        String result =restTemplate.exchange(hyperlink, HttpMethod.GET,entity,String.class).getBody();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<String, String>();

        try{
            //map = mapper.readValue(result, new TypeReference<Map<String, String>>(){});
            List<HashMap> dataAsMap = mapper.readValue(result, List.class);
            finalResult = Integer.parseInt(dataAsMap.get(0).get("accountId").toString());
            if(dataAsMap.isEmpty()){
                return finalResult;
            }
               //finalResult= map.get("accountId").toString();

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalResult;
    }

    public String getAccountDetails(String username){
        String finalResult = "";
        int accountId = this.getAccountId(username);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Identity","T49");
        headers.add("Token", "bad9e5d0-1800-4c8e-9286-b96453f75809");
        HttpEntity<String> entity  = new HttpEntity<String>(headers);
        String hyperlink = "http://techtrek-api-gateway.ap-southeast-1.elasticbeanstalk.com/accounts/deposit/"+accountId+"/balance?month=1&year=2018";
        String result =restTemplate.exchange(hyperlink, HttpMethod.GET,entity,String.class).getBody();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<String, String>();

        try{
            map = mapper.readValue(result, new TypeReference<Map<String, String>>(){});
            if(map.isEmpty()){
                return finalResult;
            }
            finalResult = mapper.writeValueAsString(map);
            //List<HashMap> dataAsMap = mapper.readValue(result, List.class);
            //finalResult = mapper.writeValueAsString(dataAsMap.get(0));
//            if(dataAsMap.isEmpty()){
//                return finalResult;
//            }
            //finalResult= map.get("accountId").toString();

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalResult;
    }

    public String collectTransct(String username){
        String finalResult = "";
        int accountId  = this.getAccountId(username);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Identity","T49");
        headers.add("Token", "bad9e5d0-1800-4c8e-9286-b96453f75809");
        HttpEntity<String> entity  = new HttpEntity<String>(headers);
        //String accountNo = Integer.toString(accountId);
        String hyperlink = "http://techtrek-api-gateway.ap-southeast-1.elasticbeanstalk.com/transactions/"+accountId+"?from=01-" +
                "01-2018&to=01-30-2020";
        String result =restTemplate.exchange(hyperlink, HttpMethod.GET,entity,String.class).getBody();
        ObjectMapper mapper = new ObjectMapper();
        //ArrayList<a> map = new HashMap<String, Object>();
        HashMap<String,Integer> answerMap = new HashMap<String,Integer>();
        try{
            List<Map<String,Object>> transct = mapper.readValue(result, new TypeReference<ArrayList<Map<String, Object>>>(){});
            for (int i=0; i<transct.size(); i++){
                Map<String,Object> map = new HashMap<String,Object>() ;
                map = transct.get(i);
                for (Map.Entry<String, Object> entry : map.entrySet()){
                    if (entry.getKey().equals("tag")) {
                        String word =entry.getValue().toString();
                        if (answerMap.containsKey(entry.getValue())) {
                            int count = answerMap.get(entry.getValue());
                            answerMap.put(word, count + 1);
                        }
                        else {answerMap.put(word,1);
                        }
                    }
                }
            }
            finalResult = mapper.writeValueAsString(answerMap);

        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalResult;
    }

//    public String collectTransctAmt(String username){
//        String finalResult = "";
//        int accountId  = this.getAccountId(username);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("Identity","T49");
//        headers.add("Token", "bad9e5d0-1800-4c8e-9286-b96453f75809");
//        HttpEntity<String> entity  = new HttpEntity<String>(headers);
//        //String accountNo = Integer.toString(accountId);
//        String hyperlink = "http://techtrek-api-gateway.ap-southeast1.elasticbeanstalk.com/transactions/"+accountId+"?from=01-" +
//                "01-2018&to=01-30-2020";
//        String result =restTemplate.exchange(hyperlink, HttpMethod.GET,entity,String.class).getBody();
//        System.out.println(result);
//        ObjectMapper mapper = new ObjectMapper();
//        //ArrayList<a> map = new HashMap<String, Object>();
//        HashMap<String,Double> answerMap = new HashMap<String,Double>();
//        try{
//            List<Map<String,Object>> transct = mapper.readValue(result, new TypeReference<ArrayList<Map<String, Object>>>(){});
//            for (int i=0; i<transct.size(); i++){
//                Map<String,Object> map = new HashMap<String,Object>() ;
//                map = transct.get(i);
//                for (Map.Entry<String, Object> entry : map.entrySet()){
//                    if (entry.getKey().equals("tag")) {
//                        String word =entry.getValue().toString();
//                        if (answerMap.containsKey(entry.getValue())) {
//                            Double amount = answerMap.get(entry.getValue());
//                            answerMap.put(word, amount + (Double)entry.getValue());
//                        }
//                        else {answerMap.put(word,(Double)entry.getValue());
//                        }
//                    }
//                }
//            }
//            finalResult = mapper.writeValueAsString(answerMap);
//
//        }catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return finalResult;
//    }


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
