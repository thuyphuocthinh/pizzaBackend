package com.website.orderPizza.entity;

import com.website.orderPizza.entity.keys.KeysOrderDetail;
import jakarta.persistence.*;

@Entity(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    KeysOrderDetail keysOrderDetail;

    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    private Products products;

    @ManyToOne
    @JoinColumn(name = "order_id", updatable = false, insertable = false)
    private Orders orders;

    public KeysOrderDetail getKeysOrderDetail() {
        return keysOrderDetail;
    }

    public void setKeysOrderDetail(KeysOrderDetail keysOrderDetail) {
        this.keysOrderDetail = keysOrderDetail;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
