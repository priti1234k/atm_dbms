package com.bank.atm.models;

import lombok.Data;

@Data
public class CustomerDetails {

    private String name;

    private String aadhar;

    private String email;

    private String mobile;
}
