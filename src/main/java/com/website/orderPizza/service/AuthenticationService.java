package com.website.orderPizza.service;

import com.website.orderPizza.entity.Users;
import com.website.orderPizza.payload.request.LoginRequest;
import com.website.orderPizza.payload.request.SignupRequest;
import com.website.orderPizza.repository.UsersRepository;
import com.website.orderPizza.service.imp.AuthenticationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationServiceImp {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public boolean login(LoginRequest loginRequest) {
        Users users = usersRepository.findByEmail(loginRequest.getEmail());
        return passwordEncoder.matches(loginRequest.getPassword(), users.getPassword());
    }

    @Override
    public boolean signup(SignupRequest signupRequest) {
        Users users = new Users();
        users.setEmail(signupRequest.getEmail());
        users.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        try {
            usersRepository.save(users);
            return true;
        } catch (Exception e) {
            System.out.println("Error signup: " + e.getMessage());
            return false;
        }
    }
}
