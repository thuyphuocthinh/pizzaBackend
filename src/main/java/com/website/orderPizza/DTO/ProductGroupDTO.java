package com.website.orderPizza.DTO;

import com.website.orderPizza.entity.Products;

import java.util.List;
import java.util.Set;

public class ProductGroupDTO {
    private int productGroupId;
    private String productGroupName;
    List<ProductsDTO> productsDTOList;

    public int getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public List<ProductsDTO> getProductsDTOList() {
        return productsDTOList;
    }

    public void setProductsDTOList(List<ProductsDTO> productsDTOList) {
        this.productsDTOList = productsDTOList;
    }
}
