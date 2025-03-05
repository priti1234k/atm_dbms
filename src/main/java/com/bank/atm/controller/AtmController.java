package com.bank.atm.controller;


import com.bank.atm.models.AtmBalanceUpdate;
import com.bank.atm.models.AtmDetails;
import com.bank.atm.models.AtmId;
import com.bank.atm.repositories.entities.Bank;
import com.bank.atm.services.AdminService;
import com.bank.atm.services.AtmService;
import com.bank.atm.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bank.atm.constants.URIPaths.*;

@RestController
@RequestMapping(API_V1 + ATM)
public class AtmController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BankService bankService;

    @Autowired
    private AtmService atmService;

    @GetMapping
    public ResponseEntity<List<AtmDetails>> listAtms(@RequestParam("adminId") Long id,@RequestParam("bankId") Long bankId){
        adminService.validateAdmin(id);
        List<AtmDetails> listOfAtms = atmService.listAtms(bankId);
        return ResponseEntity.ok(listOfAtms);
    }

    @PostMapping
    public ResponseEntity<AtmId> createAtm(@RequestParam("adminId") Long id, @RequestBody AtmDetails atmDetails){
        adminService.validateAdmin(id);
        Bank retrievedBank = bankService.validateAndGetBank(atmDetails.getBankId());
        AtmId atmId = atmService.createAtm(atmDetails, retrievedBank);
        return ResponseEntity.ok(atmId);
    }

    @PutMapping(BALANCE)
    public ResponseEntity<?> updateBalance(@RequestParam("adminId") Long id, @RequestBody AtmBalanceUpdate atmBalanceUpdate){
        adminService.validateAdmin(id);
        atmService.updateAtmBalance(atmBalanceUpdate);
        return ResponseEntity.ok().build();
    }

    @PostMapping(BALANCE)
    public ResponseEntity<?> updateAtmBalanceWithCurrentAmount(@RequestParam("adminId") Long id, @RequestBody AtmBalanceUpdate atmBalanceUpdate){
        adminService.validateAdmin(id);
        atmService.updateAtmBalanceWithCurrentAmount(atmBalanceUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAtm(@RequestParam("adminId") Long id, @RequestParam("atmId") Long atmid){
        adminService.validateAdmin(id);
        atmService.deleteAtm(atmid);
        return ResponseEntity.ok().build();
    }
}
