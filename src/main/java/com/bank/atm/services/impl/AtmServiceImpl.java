package com.bank.atm.services.impl;

import com.bank.atm.exception.*;
import com.bank.atm.models.AtmBalanceUpdate;
import com.bank.atm.models.AtmDetails;
import com.bank.atm.models.AtmId;
import com.bank.atm.repositories.AtmRepository;
import com.bank.atm.repositories.entities.Atm;
import com.bank.atm.repositories.entities.Bank;
import com.bank.atm.services.AtmService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AtmServiceImpl implements AtmService {

    @Autowired
    private AtmRepository atmRepository;

    @Override
    public List<AtmDetails> listAtms(Long bankId) {
        List<Atm> list = atmRepository.findAllAtm(bankId);
        List<AtmDetails> atmDetailsList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Atm atm : list) {
                atmDetailsList.add(convertToAtmDetails(atm));
            }
        }else{
            throw new AtmNotFoundException("No Atm present in DB");
        }
        return atmDetailsList;
    }

    @Override
    public void validateIfATMExistsWithinBank(Long bankId) {
        if(atmRepository.findByBankId(bankId) > 0) {
            throw new AtmFoundException("Atm count within the bank id " + bankId + " is greater than 0");
        }
    }

    private AtmDetails convertToAtmDetails(Atm atm) {
        AtmDetails atmDetails = new AtmDetails();
        atmDetails.setAtmBranch(atm.getAtmBranch());
        atmDetails.setAtmCode(atm.getAtmCode());
        atmDetails.setBankId(atm.getBank().getId());
        atmDetails.setBalance(atm.getBalance());
        return atmDetails;
    }

    @Override
    public AtmId createAtm(AtmDetails atmDetails, Bank bank) {
        if(Objects.nonNull(atmDetails) &&
                Objects.nonNull(atmDetails.getAtmBranch()) &&
                Objects.nonNull(atmDetails.getAtmCode()) &&
                Objects.nonNull(atmDetails.getBalance())){
            Atm atm = convertToAtm(atmDetails, bank);
            try {
                atm = atmRepository.save(atm);
            }catch (Exception ex){
                throw new AtmCreationFailedException(ex.getMessage());
            }
            AtmId atmId = new AtmId();
            atmId.setId(atm.getId());
            return atmId;
        }else{
            throw new AtmCreationFailedException("Invalid Atm Details");
        }
    }

    @Override
    @Transactional
    public void updateAtmBalance(AtmBalanceUpdate atmBalanceUpdate) {
        if(Objects.nonNull(atmBalanceUpdate) &&
                Objects.nonNull(atmBalanceUpdate.getId()) &&
                Objects.nonNull(atmBalanceUpdate.getBalance())){
            try {
                // Retrieve the ATM using ATM_ID
                Optional<Atm> atmOptional = atmRepository.findById(atmBalanceUpdate.getId());
                if (atmOptional.isPresent()) {
                    Atm atm = atmOptional.get();
                    // Update the balance in the retrieved ATM object
                    atm.setBalance(atm.getBalance() + atmBalanceUpdate.getBalance());
                    // Save the updated ATM object in the DB
                    atmRepository.save(atm);
                } else {
                    // Throw exception
                    throw new AtmNotFoundException("Atm not present in db");
                }
            } catch (Exception ex) {
                // Throw exception
                throw new AtmUpdateFailedException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            throw new AtmUpdateFailedException("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void updateAtmBalanceWithCurrentAmount(AtmBalanceUpdate atmBalanceUpdate) {
        if(Objects.nonNull(atmBalanceUpdate) &&
                Objects.nonNull(atmBalanceUpdate.getId()) &&
                Objects.nonNull(atmBalanceUpdate.getBalance())){
            try {
                // Retrieve the ATM using ATM_ID
                Optional<Atm> atmOptional = atmRepository.findById(atmBalanceUpdate.getId());
                if (atmOptional.isPresent()) {
                    Atm atm = atmOptional.get();
                    // Update the balance in the retrieved ATM object
                    atm.setBalance(atmBalanceUpdate.getBalance());
                    // Save the updated ATM object in the DB
                    atmRepository.save(atm);
                } else {
                    // Throw exception
                    throw new AtmNotFoundException("Atm not present in db");
                }
            } catch (Exception ex) {
                // Throw exception
                throw new AtmUpdateFailedException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            throw new AtmUpdateFailedException("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @Override
    public void deleteAtm(Long atmid) {
        if(Objects.nonNull(atmid)){
            try {
                atmRepository.deleteById(atmid);
            } catch (Exception ex) {
                // Throw exception
                throw new AtmDeleteFailedException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            throw new AtmDeleteFailedException("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Double atmBalanceCheck() {
        Double balance = atmRepository.findAtmBalance();
        return balance;
    }

    private Atm convertToAtm(AtmDetails atmDetails, Bank bank) {
        Atm atm = new Atm();
        atm.setAtmBranch(atmDetails.getAtmBranch());
        atm.setAtmCode(atmDetails.getAtmCode());
        atm.setBalance(atmDetails.getBalance());
        atm.setBank(bank);
        return atm;
    }
}
