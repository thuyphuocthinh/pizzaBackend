package com.website.orderPizza.repository;

import com.website.orderPizza.entity.OrderDetail;
import com.website.orderPizza.entity.keys.KeysOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, KeysOrderDetail> {
    List<OrderDetail> findByOrdersId(int id);
}
