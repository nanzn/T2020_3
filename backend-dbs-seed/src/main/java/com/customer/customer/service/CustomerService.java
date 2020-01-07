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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void getAllCustomer(int customerId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Identity","T49");
        headers.add("Token", "bad9e5d0-1800-4c8e-9286-b96453f75809");
        HttpEntity<String> entity  = new HttpEntity<String>(headers);
        String hyperlink = "http://techtrek-api-gateway.ap-southeast-1.elasticbeanstalk.com/customers/"+customerId+"/details";
        String result =restTemplate.exchange(hyperlink, HttpMethod.GET,entity,String.class).getBody();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<String, Object>();
        try{
            map = mapper.readValue(result, new TypeReference<Map<String, Object>>(){});

//            Customer cust = new Customer(map.get("customerId").toString(),
//                    map.get("gender").toString(),
//                    map.get("firstName").toString(),
//                    map.get("lastName").toString(),
//                    map.get("lastLogIn").toString(),
//                    map.get("dateOfBirth"),
//                    map.get("riskLevel"));
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
