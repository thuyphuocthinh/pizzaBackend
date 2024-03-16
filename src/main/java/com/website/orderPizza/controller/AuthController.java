package com.website.orderPizza.controller;

import com.website.orderPizza.payload.ResponseData;
import com.website.orderPizza.payload.request.LoginRequest;
import com.website.orderPizza.payload.request.SignupRequest;
import com.website.orderPizza.service.imp.AuthenticationServiceImp;
import com.website.orderPizza.utils.JwtUtilsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    @Autowired
    AuthenticationServiceImp authenticationServiceImp;
    @Autowired
    JwtUtilsHelper jwtUtilsHelper;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        ResponseData responseData = new ResponseData();
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println(secretString);
        if (authenticationServiceImp.login(loginRequest)) {
            String token = jwtUtilsHelper.generateToken(loginRequest.getEmail());
            responseData.setData("");
            responseData.setToken(token);
            responseData.setDescription("Login Successfully");
        } else {
            responseData.setData("");
            responseData.setToken("");
            responseData.setDescription(("Login Failed"));
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        ResponseData responseData = new ResponseData();
        if (authenticationServiceImp.signup(signupRequest)) {
            responseData.setStatusCode(200);
            responseData.setDescription("Registerred successfully");
        } else {
            responseData.setStatusCode(200);
            responseData.setDescription("Registered unsuccessfully");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
