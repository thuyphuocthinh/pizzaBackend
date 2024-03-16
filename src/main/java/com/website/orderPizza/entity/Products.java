package com.website.orderPizza.entity;

import jakarta.persistence.*;

@Entity(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "product_group_id")
    private ProductGroup productGroup;

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
