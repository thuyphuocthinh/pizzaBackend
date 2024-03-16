package com.website.orderPizza.service;

import com.website.orderPizza.DTO.OrderDetailDTO;
import com.website.orderPizza.DTO.OrdersDTO;
import com.website.orderPizza.DTO.ProductsDTO;
import com.website.orderPizza.entity.OrderDetail;
import com.website.orderPizza.entity.Orders;
import com.website.orderPizza.entity.Products;
import com.website.orderPizza.entity.Users;
import com.website.orderPizza.entity.keys.KeysOrderDetail;
import com.website.orderPizza.payload.request.OrdersRequest;
import com.website.orderPizza.payload.request.QuantityPriceRequest;
import com.website.orderPizza.repository.OrderDetailRepository;
import com.website.orderPizza.repository.OrdersRepository;
import com.website.orderPizza.repository.ProductsRepository;
import com.website.orderPizza.repository.UsersRepository;
import com.website.orderPizza.service.imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService implements OrdersServiceImp {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Override
    public boolean addNewOrder(OrdersRequest ordersRequest) {
        Orders orders = new Orders();
        Users users = new Users();
        users.setId(ordersRequest.getUserId());
        orders.setUsers(users);
        orders.setCreatedDate(ordersRequest.getCreatedDate());
        orders.setShippedDate(ordersRequest.getShippedDate());
        orders.setStatus(ordersRequest.getStatus());
        ordersRepository.save(orders);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        double totalPrice = 0;
        for(QuantityPriceRequest quantityPrice : ordersRequest.getQuantityPriceList()) {
            double price = 0;
            OrderDetail orderDetail = new OrderDetail();
            Optional<Products> products = productsRepository.findById(quantityPrice.getProductId());
            KeysOrderDetail keysOrderDetail = new KeysOrderDetail();
            keysOrderDetail.setProductId(quantityPrice.getProductId());
            keysOrderDetail.setOrderId(orders.getId());
            orderDetail.setKeysOrderDetail(keysOrderDetail);
            orderDetail.setQuantity(quantityPrice.getQuantity());
            if (products.isPresent()) {
                price = products.get().getUnitPrice();
                orderDetail.setPrice(quantityPrice.getQuantity() * price);
                totalPrice += quantityPrice.getQuantity() * price;
            }
            orderDetailList.add(orderDetail);
        }
        orders.setTotalPrice(totalPrice);
        try {
            orderDetailRepository.saveAll(orderDetailList);
            return true;
        } catch (Exception e) {
            System.out.println("Error add new order: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<OrdersDTO> getAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        for(Orders orders : ordersList) {
            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrdersId(orders.getId());
            List<OrderDetailDTO> orderDetailDTOList = getOrderDetailDTOS(orderDetailList);
            OrdersDTO ordersDTO = new OrdersDTO();
            ordersDTO.setOrderId(orders.getId());
            ordersDTO.setCreatedDate(orders.getCreatedDate());
            ordersDTO.setShippedDate(orders.getShippedDate());
            Optional<Users> usersOptional = usersRepository.findById(orders.getUsers().getId());
            if (usersOptional.isPresent()) {
                Users users = new Users();
                users.setId(usersOptional.get().getId());
                users.setUsername(usersOptional.get().getUsername());
                users.setAddress(usersOptional.get().getAddress());
                users.setPhoneNumber(usersOptional.get().getPhoneNumber());
                users.setEmail(usersOptional.get().getEmail());
                ordersDTO.setUsers(users);
            }
            ordersDTO.setStatus(orders.getStatus());
            ordersDTO.setTotalPrice(orders.getTotalPrice());
            ordersDTO.setOrderDetailList(orderDetailDTOList);
            ordersDTOList.add(ordersDTO);
        }
        return ordersDTOList;
    }

    private static List<OrderDetailDTO> getOrderDetailDTOS(List<OrderDetail> orderDetailList) {
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (OrderDetail orderDetail: orderDetailList) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setOrderId(orderDetail.getOrders().getId());
            orderDetailDTO.setPrice(orderDetail.getPrice());
            orderDetailDTO.setQuantity(orderDetail.getQuantity());
            ProductsDTO productsDTO = new ProductsDTO();
            productsDTO.setProductName(orderDetail.getProducts().getProductName());
            productsDTO.setId(orderDetail.getProducts().getId());
            productsDTO.setImage(orderDetail.getProducts().getImage());
            productsDTO.setDescription(orderDetail.getProducts().getDescription());
            productsDTO.setProductGroupId(orderDetail.getProducts().getProductGroup().getId());
            productsDTO.setUnitPrice(orderDetail.getProducts().getUnitPrice());
            orderDetailDTO.setProductsDTO(productsDTO);
            orderDetailDTOList.add(orderDetailDTO);
        }
        return orderDetailDTOList;
    }
}







