package com.bank.atm.services.impl;

import com.bank.atm.exception.*;
import com.bank.atm.models.BankDetails;
import com.bank.atm.models.BankId;
import com.bank.atm.repositories.BankRepository;
import com.bank.atm.repositories.entities.Bank;
import com.bank.atm.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hibernate.sql.InFragment.NOT_NULL;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public List<BankDetails> listBanks() {
        List<Bank> list = bankRepository.findAllBanks();
        List<BankDetails> bankDetailsList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Bank bank : list) {
                bankDetailsList.add(convertToBankDetails(bank));
            }
        }else{
            throw new BankNotFoundException("No bank present in DB");
        }
        return bankDetailsList;
    }

    @Override
    public BankId createBank(BankDetails bankDetails) {
        if(Objects.nonNull(bankDetails) &&
                Objects.nonNull(bankDetails.getName()) &&
                Objects.nonNull(bankDetails.getBranch()) &&
                Objects.nonNull(bankDetails.getIfscCode())){
            Bank bank = convertToBank(bankDetails);
            try {
                bank = bankRepository.save(bank);
            }catch (Exception ex){
                throw new BankCreationFailedException(ex.getMessage());
            }
            BankId bankId = new BankId();
            bankId.setId(bank.getId());
            return bankId;
        }else{
            throw new BankCreationFailedException("Invalid Bank Details");
        }
    }

    @Override
    public Bank validateAndGetBank(Long id) {
        if(Objects.nonNull(id)){
            Optional<Bank> bankOptional = bankRepository.findById(id);
            if (bankOptional.isPresent()) {
                return bankOptional.get();
            }
        }
        throw new BankNotFoundException("Bank Not Found");
    }

    @Override
    public void deleteBank(Long bankid) {
        if(Objects.nonNull(bankid)){
            try {
                bankRepository.deleteById(bankid);
            } catch (Exception ex) {
                    // Throw exception
                throw new BankDeleteFailedException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
                throw new BankDeleteFailedException("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Bank validateAndGetBankIfsc(String ifscCode) {
        if(Objects.nonNull(ifscCode)){
            Bank bank = bankRepository.findBankIfsc(ifscCode);
            return bank;
        }else{
            throw new BankNotFoundException("Bank Not Found");
        }
    }

    private BankDetails convertToBankDetails(Bank bank){
        BankDetails bankDetails = new BankDetails();
        bankDetails.setName(bank.getName());
        bankDetails.setBranch(bank.getBranch());
        bankDetails.setIfscCode(bank.getIfscCode());
        return bankDetails;
    }

    private Bank convertToBank(BankDetails bankDetails){
        Bank bank = new Bank();
        bank.setName(bankDetails.getName());
        bank.setBranch(bankDetails.getBranch());
        bank.setIfscCode(bankDetails.getIfscCode());
        return bank;
    }
}
