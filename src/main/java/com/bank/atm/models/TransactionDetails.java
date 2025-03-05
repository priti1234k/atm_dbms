package com.bank.atm.models;


import com.bank.atm.repositories.entities.Card;
import lombok.Data;

@Data
public class TransactionDetails {

    private Double balance;

    private TransactionType transactionType;

    private Card card;
}
