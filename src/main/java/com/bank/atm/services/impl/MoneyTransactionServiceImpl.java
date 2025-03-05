package com.bank.atm.services.impl;

import com.bank.atm.exception.*;
import com.bank.atm.models.*;
import com.bank.atm.repositories.MoneyTransactionRepository;
import com.bank.atm.repositories.TransactionRepository;
import com.bank.atm.repositories.entities.Atm;
import com.bank.atm.repositories.entities.Bank;
import com.bank.atm.repositories.entities.Card;
import com.bank.atm.repositories.entities.Transactions;
import com.bank.atm.services.MoneyTransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MoneyTransactionServiceImpl implements MoneyTransactionService {

    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public CardBalanceCheck balanceCheck(String cardNo) {
        CardBalanceCheck cardBalanceCheck = new CardBalanceCheck();
        if(Objects.nonNull(cardNo)){
            Double balance = moneyTransactionRepository.findBalanceUsingByCardNo(cardNo);
            if(balance == null ) {
                throw new CardNotFoundException("Invalid Card No.");
            }
            cardBalanceCheck.setBalance(balance);
            return cardBalanceCheck;
        }
       throw new CardNotFoundException("Invalid Card No.");
    }

    @Override
    @Transactional
    public void pinUpdate(CardLoginDetails cardLoginDetails) {
        if(Objects.nonNull(cardLoginDetails) &&
                Objects.nonNull(cardLoginDetails.getCardNo()) &&
                Objects.nonNull(cardLoginDetails.getPin())){
            try {
                // Retrieve the ATM using ATM_ID
                Card card = moneyTransactionRepository.findByCardNo(cardLoginDetails.getCardNo());
                if (Objects.nonNull(card)) {
                    // Update the balance in the retrieved ATM object
                    card.setPin(cardLoginDetails.getPin());
                    // Save the updated ATM object in the DB
                    moneyTransactionRepository.save(card);
                } else {
                    // Throw exception
                    throw new CardNotFoundException("card not present in db");
                }
            } catch (Exception ex) {
                // Throw exception
                throw new PinUpdateFailedException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            throw new PinUpdateFailedException("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void validateCardNo(String cardNo) {
        if(moneyTransactionRepository.findByCardNoCount(cardNo) == 0) {
            throw new CardNotFoundException("Invalid Card No.");
        }
    }

    @Override
    @Transactional
    public void withdrawMoney(String cardNo, Double amount) {
        CardBalanceUpdate cardBalanceUpdate = new CardBalanceUpdate();
        if(Objects.nonNull(cardNo) &&
            Objects.nonNull(amount)){
            try {
                Card card = moneyTransactionRepository.findByCardNo(cardNo);
                if (Objects.nonNull(card)) {
                    // Update the balance in the retrieved ATM object
                    card.setBalance(amount);
                    // Save the updated ATM object in the DB
                    moneyTransactionRepository.save(card);
                } else {
                    throw new CardNotFoundException("Invalid Card No.");
                }
            }catch (Exception ex){
                throw new CardBalanceUpdateFailedException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            throw new CardNotFoundException("Invalid Details");
        }

    }

    @Override
    public TransactionId createTransaction(TransactionDetails transactionDetails) {
            if(Objects.nonNull(transactionDetails) &&
                    Objects.nonNull(transactionDetails.getCard()) &&
                    Objects.nonNull(transactionDetails.getBalance()) &&
                    Objects.nonNull(transactionDetails.getTransactionType())){
                Transactions transactions = convertToTransaction(transactionDetails);
                try {
                    transactions = transactionRepository.save(transactions);
                }catch (Exception ex){
                    throw new AtmCreationFailedException(ex.getMessage());
                }
                TransactionId transactionId = new TransactionId();
                transactionId.setTransaction_id(transactions.getId());
                return transactionId;
            }else{
                throw new TransactionsCreationFailedException("Invalid Card Details");
            }
    }

    private Transactions convertToTransaction(TransactionDetails transactionDetails) {
        Transactions transactions = new Transactions();
        transactions.setTransactionType(transactionDetails.getTransactionType());
        transactions.setCard(transactionDetails.getCard());
        transactions.setBalance(transactionDetails.getBalance());
        return transactions;
    }


}
