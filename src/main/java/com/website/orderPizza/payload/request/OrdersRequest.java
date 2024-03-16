package com.website.orderPizza.payload.request;

import java.sql.Date;
import java.util.List;

public class OrdersRequest {
    private int userId;
    private Date createdDate;
    private Date shippedDate;
    private String status;
    private List<QuantityPriceRequest> quantityPriceList;

    public List<QuantityPriceRequest> getQuantityPriceList() {
        return quantityPriceList;
    }

    public void setQuantityPriceList(List<QuantityPriceRequest> quantityPriceList) {
        this.quantityPriceList = quantityPriceList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
