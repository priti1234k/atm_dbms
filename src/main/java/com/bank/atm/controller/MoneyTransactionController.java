package com.bank.atm.controller;

import com.bank.atm.exception.AtmBalanceNotFoundException;
import com.bank.atm.exception.CardBalanceNotFoundException;
import com.bank.atm.exception.CardNotFoundException;
import com.bank.atm.models.*;
import com.bank.atm.repositories.entities.Atm;
import com.bank.atm.repositories.entities.Card;
import com.bank.atm.services.AtmService;
import com.bank.atm.services.CardService;
import com.bank.atm.services.MoneyTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.bank.atm.constants.URIPaths.*;

@RestController
@RequestMapping(API_V1)
public class MoneyTransactionController {

    @Autowired
    private MoneyTransactionService moneyTransactionService;

    @Autowired
    private AtmService atmService;

    @Autowired
    private CardService cardService;

    @GetMapping(BALANCE)
    public ResponseEntity<CardBalanceCheck> balanceCheck(@RequestParam("cardNo") String cardNo){
         CardBalanceCheck cardBalanceCheck = moneyTransactionService.balanceCheck(cardNo);
        return ResponseEntity.ok(cardBalanceCheck);
    }

    @PutMapping(UPDATE + PIN)
    public ResponseEntity<?> updateCardPin(@RequestBody CardLoginDetails cardLoginDetails){
        moneyTransactionService.pinUpdate(cardLoginDetails);
        return ResponseEntity.ok().build();
    }

    @GetMapping(WITHDRAW)
    public ResponseEntity<TransactionId> withdrawMoney(@RequestParam("cardNo") String cardNo, @RequestParam("amount") Double amount){
        moneyTransactionService.validateCardNo(cardNo);
        if(atmService.atmBalanceCheck() >= amount){
            Card card = cardService.cardBalanceCheck(cardNo);
            Double cardBalance = card.getBalance();
            if(cardBalance >= amount){
                moneyTransactionService.withdrawMoney(cardNo, amount);
                AtmBalanceUpdate atmBalanceUpdate= new AtmBalanceUpdate();
                atmBalanceUpdate.setId((long)1);
                atmBalanceUpdate.setBalance(amount);
                atmService.updateAtmBalance(atmBalanceUpdate);
                TransactionDetails transactionDetails =new TransactionDetails();
                transactionDetails.setTransactionType(TransactionType.DEBIT);
                transactionDetails.setCard(card);
                transactionDetails.setBalance(amount);
                TransactionId transactionId = moneyTransactionService.createTransaction(transactionDetails);
                return ResponseEntity.ok(transactionId);
            }else{
                throw new CardBalanceNotFoundException("Card balance is low");
            }
        }else{
            throw new AtmBalanceNotFoundException("Atm balance is low");
        }
    }

//    @PutMapping(DEPOSIT)
//    public ResponseEntity<?> depositMoney(@RequestBody CardBalanceUpdate cardBalanceUpdate){
//
//    }
//
//    @GetMapping(TRANSACTION)
//    public ResponseEntity<List<TransactionDetails>> listOfTransaction(@RequestParam("cardNo") String cardNo, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate){
//
//    }
//
//    @GetMapping(TRANSFER)
//    public ResponseEntity<TransactionId> transferMoneyToAnotherCard(@RequestBody TransferMoneyDetails transferMoneyDetails){
//
//    }

}
