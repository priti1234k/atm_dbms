package com.bank.atm.controller;

import com.bank.atm.models.CustomerDetails;
import com.bank.atm.models.CustomerId;
import com.bank.atm.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bank.atm.constants.URIPaths.API_V1;
import static com.bank.atm.constants.URIPaths.CUSTOMER;

@RestController
@RequestMapping(API_V1 + CUSTOMER)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerId> createCustomer(@RequestBody CustomerDetails customerDetails){
        CustomerId customerId = customerService.createCustomer(customerDetails);
        return ResponseEntity.ok(customerId);
    }
}
