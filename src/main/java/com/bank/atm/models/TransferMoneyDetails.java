package com.bank.atm.models;

import lombok.Data;

@Data
public class TransferMoneyDetails {

    private String cardNo;

    private String sendToCardNo;

    private Double amount;
}
