package com.bank.atm.services;

import com.bank.atm.models.*;
import com.bank.atm.repositories.entities.Bank;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AtmService {

    List<AtmDetails> listAtms(Long bankId);

    void validateIfATMExistsWithinBank(Long bankId);

    AtmId createAtm(AtmDetails atmDetails, Bank bank);

    void updateAtmBalance(AtmBalanceUpdate atmBalanceUpdate);

    void updateAtmBalanceWithCurrentAmount(AtmBalanceUpdate atmBalanceUpdate);

    @Transactional
    void deleteAtm(Long atmid);

    Double atmBalanceCheck();
}
