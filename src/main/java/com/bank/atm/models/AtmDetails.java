package com.bank.atm.models;

import lombok.Data;

@Data
public class AtmDetails {

    private String atmBranch;

    private String atmCode;

    private Long bankId;

    private Double balance;
}
