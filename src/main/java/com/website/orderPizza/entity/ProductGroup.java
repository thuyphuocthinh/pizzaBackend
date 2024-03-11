package com.website.orderPizza.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "product_group")
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_group_name")
    private String productGroupName;

    @OneToMany(mappedBy = "productGroup")
    Set<Products> productsSet;

    public Set<Products> getProductsSet() {
        return productsSet;
    }

    public void setProductsSet(Set<Products> productsSet) {
        this.productsSet = productsSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }
}
