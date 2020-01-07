package com.customer.customer.api;

import com.customer.customer.model.Customer;
import com.customer.customer.service.CustomerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value="/api")
public class CustomerDetailAPI {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value="/customers/all")
    public List<Customer> getAllCustomers(Model model) {
        return customerService.getAllCustomers();
    }

//    @GetMapping(value="/customers/{customerId}")
//    public void updateMember(@PathVariable("customerId") int customerId){
//        customerService.saveCustomer(customerId);
//    }

    @GetMapping(value="/customers/getName/{username}")
    public String getUsername(@PathVariable("username") String username){
        return customerService.getCustomerName(username);
    }

    @GetMapping(value="/customers/getRiskLevel/{username}")
    public String getRiskLevel(@PathVariable("username") String username){
        return customerService.getRiskLevel(username);
    }

    @GetMapping(value="/customers/getCustomerId/{username}")
    public String getCustomerId(@PathVariable("username") String username){
        System.out.println("hi");
        return customerService.getCustomerId(username);
    }

    @GetMapping(value="/customers/getAccountId/{username}")
    public int getAccountId(@PathVariable("username") String username){
        return customerService.getAccountId(username);
    }

    @GetMapping(value="/customers/getAccountDetail/{username}")
    public String getAccountDetail(@PathVariable("username") String username){
        return customerService.getAccountDetails(username);
    }

    @GetMapping(value="/customers/getTrans/{username}")
    public String getTransaction(@PathVariable("username") String username){
         return customerService.collectTransct(username);
    }

//    @GetMapping(value="/customers/getTransAmt/{username}")
//    public String getTransactionAmt(@PathVariable("username") String username){
//        return customerService.collectTransctAmt(username);
//    }


    @PostMapping(value = "/customers/login")
    public String getMember(@RequestBody String json ){
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<String, Object>();
        String result = "";
        try{
            map = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
            result = customerService.getSelectedMember(map.get("username").toString(), map.get("password").toString());
        }  catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
