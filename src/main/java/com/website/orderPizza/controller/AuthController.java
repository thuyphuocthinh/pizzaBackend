package com.website.orderPizza.controller;

import com.website.orderPizza.DTO.UsersDTO;
import com.website.orderPizza.entity.Users;
import com.website.orderPizza.payload.ResponseData;
import com.website.orderPizza.payload.request.LoginRequest;
import com.website.orderPizza.payload.request.SignupRequest;
import com.website.orderPizza.service.imp.AuthenticationServiceImp;
import com.website.orderPizza.service.imp.UsersServiceImp;
import com.website.orderPizza.utils.JwtUtilsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    AuthenticationServiceImp authenticationServiceImp;
    @Autowired
    JwtUtilsHelper jwtUtilsHelper;
    @Autowired
    UsersServiceImp usersServiceImp;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        ResponseData responseData = new ResponseData();
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println(secretString);
        if (authenticationServiceImp.login(loginRequest)) {
            String token = jwtUtilsHelper.generateToken(loginRequest.getEmail());
            Users users =  usersServiceImp.getUsersByEmail(loginRequest.getEmail());
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setId(users.getId());
            usersDTO.setPhoneNumber(users.getPhoneNumber());
            usersDTO.setAddress(users.getAddress());
            usersDTO.setEmail(users.getEmail());
            responseData.setData(usersDTO);
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
