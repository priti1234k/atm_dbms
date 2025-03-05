package com.bank.atm.controller;

import com.bank.atm.models.BankDetails;
import com.bank.atm.models.BankId;
import com.bank.atm.models.UserType;
import com.bank.atm.services.AdminService;
import com.bank.atm.services.AtmService;
import com.bank.atm.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bank.atm.constants.URIPaths.API_V1;
import static com.bank.atm.constants.URIPaths.BANKS;

@RestController
@RequestMapping(API_V1 + BANKS)
public class BankController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BankService bankService;

    @Autowired
    private AtmService atmService;

    @GetMapping
    public ResponseEntity<List<BankDetails>> listBanks(@RequestParam("adminId") Long id){
        adminService.validateAdmin(id);
        List<BankDetails> listOfBanks = bankService.listBanks();
        return ResponseEntity.ok(listOfBanks);
    }

    @PostMapping
    public ResponseEntity<BankId> createBank(@RequestParam("adminId") Long id, @RequestBody BankDetails bankDetails){
        adminService.validateAdmin(id);
        BankId bankId = bankService.createBank(bankDetails);
        return ResponseEntity.ok(bankId);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBank(@RequestParam("adminId") Long id, @RequestParam("bankId") Long bankid){
        adminService.validateAdmin(id);
        atmService.validateIfATMExistsWithinBank(bankid);
        bankService.deleteBank(bankid);
        return ResponseEntity.ok().build();
    }
}
