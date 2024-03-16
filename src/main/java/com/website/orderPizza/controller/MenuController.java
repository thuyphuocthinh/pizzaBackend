package com.website.orderPizza.controller;

import com.website.orderPizza.DTO.ProductGroupDTO;
import com.website.orderPizza.DTO.ProductsDTO;
import com.website.orderPizza.payload.ResponseData;
import com.website.orderPizza.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuServiceImp menuServiceImp;

    @GetMapping("/get")
    public ResponseEntity<?> getListOfProducts() {
        ResponseData responseData = new ResponseData();
        List<ProductsDTO> productsDTOList = menuServiceImp.getListOfProducts();
        if (!productsDTOList.isEmpty()) {
            responseData.setStatusCode(200);
            responseData.setDescription("Get content successfully");
        } else {
            responseData.setStatusCode(204);
            responseData.setDescription("No content");
        }
        responseData.setData(productsDTOList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getByCategory")
    public ResponseEntity<?> getProductsByCategory() {
        ResponseData responseData = new ResponseData();
        List<ProductGroupDTO> productGroupDTOList = menuServiceImp.getProductsByCategory();
        if (!productGroupDTOList.isEmpty()) {
            responseData.setStatusCode(200);
            responseData.setDescription("Get content successfully");
        } else {
            responseData.setStatusCode(204);
            responseData.setDescription("No content");
        }
        responseData.setData(productGroupDTOList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}
