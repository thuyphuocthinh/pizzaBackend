package com.website.orderPizza.controller;

import com.website.orderPizza.DTO.OrdersDTO;
import com.website.orderPizza.payload.ResponseData;
import com.website.orderPizza.payload.request.OrdersRequest;
import com.website.orderPizza.service.imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrdersServiceImp ordersServiceImp;

    @PostMapping("/add")
    public ResponseEntity<?> addNewOrder(@RequestBody OrdersRequest ordersRequest) {
        ResponseData responseData = new ResponseData();
        if (ordersServiceImp.addNewOrder(ordersRequest)) {
            responseData.setStatusCode(200);
            responseData.setDescription("Added new order successfully");
        } else {
            responseData.setStatusCode(400);
            responseData.setDescription("Failed to add new order");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllOrders() {
        ResponseData responseData = new ResponseData();
        List<OrdersDTO> ordersDTOList = ordersServiceImp.getAllOrders();
        if (!ordersDTOList.isEmpty()) {
            responseData.setStatusCode(200);
            responseData.setDescription("Get all orders successfully");
            responseData.setData(ordersDTOList);
        } else {
            responseData.setStatusCode(204);
            responseData.setData(null);
            responseData.setDescription("List of orders is empty");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
