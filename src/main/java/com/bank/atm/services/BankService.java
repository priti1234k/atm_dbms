package com.bank.atm.services;

import com.bank.atm.models.BankDetails;
import com.bank.atm.models.BankId;
import com.bank.atm.repositories.entities.Bank;

import java.util.List;

public interface BankService {

    List<BankDetails> listBanks();

    BankId createBank(BankDetails bankDetails);

    Bank validateAndGetBank(Long id);

    void deleteBank(Long bankid);

    Bank validateAndGetBankIfsc(String ifscCode);
}
