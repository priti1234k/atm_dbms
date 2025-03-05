package com.bank.atm.services.impl;

import com.bank.atm.exception.BankCreationFailedException;
import com.bank.atm.exception.BankNotFoundException;
import com.bank.atm.exception.CustomerCreationFailedException;
import com.bank.atm.models.BankId;
import com.bank.atm.models.CustomerDetails;
import com.bank.atm.models.CustomerId;
import com.bank.atm.repositories.CustomerRepository;
import com.bank.atm.repositories.entities.Bank;
import com.bank.atm.repositories.entities.Customer;
import com.bank.atm.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerId createCustomer(CustomerDetails customerDetails) {
        if(Objects.nonNull(customerDetails.getName()) &&
                Objects.nonNull(customerDetails.getAadhar()) &&
                Objects.nonNull(customerDetails.getEmail()) &&
                Objects.nonNull(customerDetails.getMobile())){
            Customer customer = convertToCustomerDetails(customerDetails);
            try{
                customer = customerRepository.save(customer);
            }catch (Exception ex){
                throw new CustomerCreationFailedException(ex.getMessage());
            }
            CustomerId customerId = new CustomerId();
            customerId.setId(customer.getId());
            return customerId;

        }else{
            throw new CustomerCreationFailedException("Invalid Customer Details");
        }
    }

    @Override
    public Customer validateAndGetCustomerId(Long customerId) {
        if(Objects.nonNull(customerId)){
            Customer customer = customerRepository.findByCustomerId(customerId);
            return customer;
        }else{
            throw new BankNotFoundException("Bank Not Found");
        }
    }

    private Customer convertToCustomerDetails(CustomerDetails customerDetails) {
        Customer customer = new Customer();
        customer.setName(customerDetails.getName());
        customer.setAadhar(customerDetails.getAadhar());
        customer.setEmail(customerDetails.getEmail());
        customer.setMobile(customerDetails.getMobile());
        return customer;
    }
}
