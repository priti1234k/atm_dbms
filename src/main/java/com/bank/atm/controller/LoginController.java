package com.bank.atm.controller;

import com.bank.atm.models.Login;
import com.bank.atm.models.UserType;
import com.bank.atm.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.bank.atm.constants.URIPaths.API_V1;
import static com.bank.atm.constants.URIPaths.LOGIN;

@RestController
@RequestMapping(API_V1 + LOGIN)
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity<Login> login(
            @RequestParam("usertype") UserType userType,
            @RequestParam("username") String userName,
            @RequestParam("password") String password) {
        Login login = loginService.login(userType, userName, password);
        return ResponseEntity.ok(login);
    }
}
