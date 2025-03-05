package com.bank.atm.models;

import lombok.Data;

@Data
public class CardDetails {

    private Long customerId;

    private String cardNo;

    private String pin;

    private String ifscCode;

    private Double balance;
}
