package com.bank.atm.services.impl;


import com.bank.atm.exception.CardCreationFailedException;
import com.bank.atm.exception.LoginException;
import com.bank.atm.models.CardDetails;
import com.bank.atm.models.CardNumber;
import com.bank.atm.repositories.CardRepository;
import com.bank.atm.repositories.entities.Bank;
import com.bank.atm.repositories.entities.Card;
import com.bank.atm.repositories.entities.Customer;
import com.bank.atm.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;
    @Override
    public CardNumber createCard(CardDetails cardDetails, Bank bank, Customer customer) {
        if(Objects.nonNull(cardDetails) &&
                Objects.nonNull(cardDetails.getCardNo()) &&
                Objects.nonNull(cardDetails.getBalance()) &&
                Objects.nonNull(cardDetails.getPin())){
            Card card = convertToCard(cardDetails, bank, customer);
            try {
                card = cardRepository.save(card);
            }catch (Exception ex){
                throw new CardCreationFailedException(ex.getMessage());
            }
            CardNumber cardNumber = new CardNumber();
            cardNumber.setCardNo(card.getCardNo());
            return cardNumber;
        }else{
            throw new CardCreationFailedException("Invalid Card Details");
        }
    }

    @Override
    public CardNumber cardLogin(String cardNo, String pin) {
        CardNumber cardNumberObj = new CardNumber();
        if(Objects.nonNull(cardNo) &&
            Objects.nonNull(pin)) {
            String card_No = cardRepository.findIdByCardNoAndPin(cardNo, pin);
            cardNumberObj.setCardNo(card_No);
        }
        if (Objects.nonNull(cardNumberObj.getCardNo())) {
            return cardNumberObj;
        }
        throw new LoginException("Card Details Invalid");

    }

    @Override
    public Card cardBalanceCheck(String cardNo) {
        Card card = cardRepository.findBalanceByCardNo(cardNo);
        return card;
    }

    private Card convertToCard(CardDetails cardDetails, Bank bank, Customer customer) {
        Card card = new Card();
        card.setCustomer(customer);
        card.setCardNo(cardDetails.getCardNo());
        card.setPin(cardDetails.getPin());
        card.setBank(bank);
        card.setBalance(cardDetails.getBalance());
        return card;
    }
}
