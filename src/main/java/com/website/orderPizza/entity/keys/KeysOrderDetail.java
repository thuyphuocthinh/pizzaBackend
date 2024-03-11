package com.website.orderPizza.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class KeysOrderDetail implements Serializable {
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "order_id")
    private int order_id;

    public KeysOrderDetail() {}

    public KeysOrderDetail(int product_id, int order_id) {
        this.order_id = order_id;
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
