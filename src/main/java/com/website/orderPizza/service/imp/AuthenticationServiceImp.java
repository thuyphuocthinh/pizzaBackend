package com.website.orderPizza.service.imp;

import com.website.orderPizza.payload.request.LoginRequest;
import com.website.orderPizza.payload.request.SignupRequest;

public interface AuthenticationServiceImp {
    boolean login(LoginRequest loginRequest);
    boolean signup(SignupRequest signupRequest);
}
