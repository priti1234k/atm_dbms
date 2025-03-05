package com.bank.atm.services.impl;

import com.bank.atm.exception.LoginException;
import com.bank.atm.models.Login;
import com.bank.atm.models.UserType;
import com.bank.atm.repositories.AdminRepository;
import com.bank.atm.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Login login(UserType userType, String userName, String password) {
        Login login = new Login();
        if (userType == UserType.ADMIN) {
            Long admin_id = adminRepository.findIdByUserNameAndPassword(userName, password);
            login.setId(admin_id);
        }
        if (Objects.nonNull(login.getId())) {
            return login;
        }
        throw new LoginException("Login Forbidden for User: "+userName);
    }
}
