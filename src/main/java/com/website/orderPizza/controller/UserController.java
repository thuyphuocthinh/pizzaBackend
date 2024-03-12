package com.website.orderPizza.controller;

import com.website.orderPizza.DTO.UsersDTO;
import com.website.orderPizza.payload.ResponseData;
import com.website.orderPizza.service.imp.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UsersServiceImp usersServiceImp;
    @GetMapping("/get")
    public ResponseEntity<?> getListOfUsers() {
        ResponseData responseData = new ResponseData();
        List<UsersDTO> usersDTOList = usersServiceImp.getListOfUsers();
        if (!usersDTOList.isEmpty()) {
            responseData.setData(usersDTOList);
            responseData.setStatusCode(200);
            responseData.setDescription("Get successfully");
        } else {
            responseData.setStatusCode(204);
            responseData.setDescription("No content");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
