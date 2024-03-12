package com.website.orderPizza.service;

import com.website.orderPizza.DTO.ProductGroupDTO;
import com.website.orderPizza.DTO.ProductsDTO;
import com.website.orderPizza.entity.ProductGroup;
import com.website.orderPizza.entity.Products;
import com.website.orderPizza.payload.ProductRequest;
import com.website.orderPizza.repository.CategoryRepository;
import com.website.orderPizza.repository.ProductsRepository;
import com.website.orderPizza.service.imp.FileServiceImp;
import com.website.orderPizza.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService implements MenuServiceImp {
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FileServiceImp fileServiceImp;

    @Override
    public List<ProductsDTO> getListOfProducts() {
        List<Products> productsList = productsRepository.findAll();
        List<ProductsDTO> productsDTOList = new ArrayList<>();
        for (Products products : productsList) {
            ProductGroup productGroup = new ProductGroup();
            productGroup.setId(products.getProductGroup().getId());
            productGroup.setProductGroupName(products.getProductGroup().getProductGroupName());
            ProductsDTO productsDTO = new ProductsDTO();
            productsDTO.setProductName(products.getProductName());
            productsDTO.setId(products.getId());
            productsDTO.setDescription(products.getDescription());
            productsDTO.setUnitPrice(products.getUnitPrice());
            productsDTO.setImage(products.getImage());
            productsDTO.setProductGroup(productGroup);
            productsDTOList.add(productsDTO);
        }
        return productsDTOList;
    }

    @Override
    public List<ProductGroupDTO> getProductsByCategory() {
        List<ProductGroup> productGroupList = categoryRepository.findAll();
        List<ProductGroupDTO> productGroupDTOList = new ArrayList<>();
        for(ProductGroup productGroup : productGroupList) {
            ProductGroupDTO productGroupDTO = new ProductGroupDTO();
            productGroupDTO.setProductGroupId(productGroup.getId());
            productGroupDTO.setProductGroupName(productGroup.getProductGroupName());
            List<ProductsDTO> productsDTOList = new ArrayList<>();
            for(Products products : productGroup.getProductsSet()) {
                ProductsDTO productsDTO = new ProductsDTO();
                productsDTO.setProductName(products.getProductName());
                productsDTO.setDescription(products.getDescription());
                productsDTO.setImage(products.getImage());
                productsDTO.setUnitPrice(products.getUnitPrice());
                productsDTO.setId(products.getId());
                productsDTOList.add(productsDTO);
            }
            productGroupDTO.setProductsDTOList(productsDTOList);
            productGroupDTOList.add(productGroupDTO);
        }
        return productGroupDTOList;
    }

    @Override
    public boolean addNewProduct(MultipartFile file, String productName, String description, String unitPrice, int productGroupId) {
        boolean isSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if (isSaveFileSuccess) {
                Products products = new Products();
                ProductGroup productGroup = new ProductGroup();
                productGroup.setId(productGroupId);
                products.setProductName(productName);
                products.setDescription(description);
                products.setUnitPrice(unitPrice);
                products.setImage(file.getOriginalFilename());
                products.setProductGroup(productGroup);
                productsRepository.save(products);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Error Add New Product : " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public void deleteById(Integer id) {
        productsRepository.deleteById(id);
    }

    @Override
    public boolean updateProduct(ProductRequest productRequest) {
        Products products = new Products();
        products.setId(productRequest.getId());
        products.setImage(productRequest.getImage());
        products.setDescription(productRequest.getDescription());
        products.setProductName(productRequest.getProductName());
        products.setUnitPrice(productRequest.getUnitPrice());
        try {
            productsRepository.save(products);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
