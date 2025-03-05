package com.bank.atm.services;

import com.bank.atm.models.*;
import com.bank.atm.repositories.entities.Bank;
import com.bank.atm.repositories.entities.Card;
import com.bank.atm.repositories.entities.Customer;

public interface CardService {

    CardNumber createCard(CardDetails cardDetails, Bank bank, Customer customer);

    CardNumber cardLogin(String cardNo, String pin);

    Card cardBalanceCheck(String cardNo);
}
