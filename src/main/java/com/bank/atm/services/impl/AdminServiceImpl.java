package com.bank.atm.services.impl;

import com.bank.atm.exception.AdminUnAuthException;
import com.bank.atm.repositories.AdminRepository;
import com.bank.atm.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void validateAdmin(Long id) {
        boolean validation=false;
        if(Objects.nonNull(id)){
            validation = adminRepository.existsById(id);
        }
        if(!validation){
            throw new AdminUnAuthException("Admin Unauthorized");
        }
    }
}
