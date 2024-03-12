package com.website.orderPizza.service.imp;

import com.website.orderPizza.DTO.ProductGroupDTO;
import com.website.orderPizza.DTO.ProductsDTO;
import com.website.orderPizza.entity.Products;
import com.website.orderPizza.payload.ProductRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuServiceImp {
    List<ProductsDTO> getListOfProducts();
    List<ProductGroupDTO> getProductsByCategory();
    boolean addNewProduct(MultipartFile file, String productName, String description, String unitPrice, int productGroupId);
    void deleteById(Integer id);
    boolean updateProduct(ProductRequest productRequest);
}
