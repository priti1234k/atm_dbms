package com.bank.atm.services;

import com.bank.atm.models.Login;
import com.bank.atm.models.UserType;

public interface LoginService {

    Login login(UserType userType, String userName, String password);
}
