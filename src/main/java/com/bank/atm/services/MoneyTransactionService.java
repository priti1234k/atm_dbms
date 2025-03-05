package com.bank.atm.services;

import com.bank.atm.models.*;

public interface MoneyTransactionService {

    CardBalanceCheck balanceCheck(String cardNo);

    void pinUpdate(CardLoginDetails cardLoginDetails);

    void validateCardNo(String cardNo);

    void withdrawMoney(String cardNo, Double amount);

    TransactionId createTransaction(TransactionDetails transactionDetails);
}
