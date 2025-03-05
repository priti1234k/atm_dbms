package com.bank.atm.services;

import com.bank.atm.models.CustomerDetails;
import com.bank.atm.models.CustomerId;
import com.bank.atm.repositories.entities.Customer;

public interface CustomerService {

    CustomerId createCustomer(CustomerDetails customerDetails);

    Customer validateAndGetCustomerId(Long customerId);
}
