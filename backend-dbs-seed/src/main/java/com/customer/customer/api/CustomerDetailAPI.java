package com.customer.customer.api;

import com.customer.customer.model.Customer;
import com.customer.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value="/customers/{customerId}")
    public void updateMember(@PathVariable("customerId") int customerId){
        customerService.getAllCustomer(customerId);
    }


}
