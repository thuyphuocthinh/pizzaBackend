package com.website.orderPizza.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class KeysOrderDetail implements Serializable {
    @Column(name = "product_id", updatable = false, insertable = false)
    private int productId;

    @Column(name = "order_id", updatable = false, insertable = false)
    private int orderId;

    public KeysOrderDetail() {}

    public KeysOrderDetail(int productid, int orderId) {
        this.orderId = orderId;
        this.productId = productid;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
