package com.website.orderPizza.service.imp;

import com.website.orderPizza.DTO.OrdersDTO;
import com.website.orderPizza.payload.request.OrdersRequest;

import java.util.List;

public interface OrdersServiceImp {
    boolean addNewOrder(OrdersRequest ordersRequest);
    List<OrdersDTO> getAllOrders();
}
