package com.bank.atm.controller;


import com.bank.atm.models.CardDetails;
import com.bank.atm.models.CardNumber;
import com.bank.atm.repositories.entities.Bank;
import com.bank.atm.repositories.entities.Customer;
import com.bank.atm.services.BankService;
import com.bank.atm.services.CardService;
import com.bank.atm.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bank.atm.constants.URIPaths.API_V1;
import static com.bank.atm.constants.URIPaths.CARD;

@RestController
@RequestMapping(API_V1 + CARD)
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private BankService bankService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CardNumber> createCard(@RequestBody CardDetails cardDetails){
        Bank reteriveBank = bankService.validateAndGetBankIfsc(cardDetails.getIfscCode());
        Customer reteriveCustomer = customerService.validateAndGetCustomerId(cardDetails.getCustomerId());
        CardNumber cardId = cardService.createCard(cardDetails, reteriveBank, reteriveCustomer);
        return ResponseEntity.ok(cardId);
    }

    @GetMapping
    public ResponseEntity<CardNumber> cardLogin(
            @RequestParam("cardNo") String cardNo,
            @RequestParam("pin") String pin) {
        CardNumber cardNumber1 = cardService.cardLogin(cardNo, pin);
        return ResponseEntity.ok(cardNumber1);
    }

}
