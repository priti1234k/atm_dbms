package com.bank.atm.models;

import lombok.Data;

@Data
public class CardBalanceUpdate {

    private String cardNo;

    private Double balance;
}
