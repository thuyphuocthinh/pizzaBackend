package com.website.orderPizza.DTO;

public class OrderDetailDTO {
    private int orderId;
    private int quantity;
    private double price;
    private ProductsDTO productsDTO;
    public int getOrderId() {
        return orderId;
    }

    public ProductsDTO getProductsDTO() {
        return productsDTO;
    }

    public void setProductsDTO(ProductsDTO productsDTO) {
        this.productsDTO = productsDTO;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
