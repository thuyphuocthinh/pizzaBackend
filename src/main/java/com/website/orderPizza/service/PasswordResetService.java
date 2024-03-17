package com.website.orderPizza.service;

import com.website.orderPizza.entity.PasswordResetToken;
import com.website.orderPizza.entity.Users;
import com.website.orderPizza.repository.PasswordResetTokenRepository;
import com.website.orderPizza.service.imp.PasswordResetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService implements PasswordResetServiceImp {
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @Override
    public void createPasswordResetTokenForUser(Users users, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setUsers(users);
        myToken.setToken(token);
        passwordResetTokenRepository.save(myToken);
    }
}
