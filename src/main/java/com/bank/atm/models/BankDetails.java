package com.bank.atm.models;

import lombok.Data;

@Data
public class BankDetails {

    private String name;

    private String branch;

    private String ifscCode;
}
