package com.website.orderPizza.service;

import com.website.orderPizza.DTO.ProductGroupDTO;
import com.website.orderPizza.entity.ProductGroup;
import com.website.orderPizza.repository.CategoryRepository;
import com.website.orderPizza.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<ProductGroupDTO> getListOfCategory() {
        List<ProductGroup> productGroupList = categoryRepository.findAll();
        List<ProductGroupDTO> productGroupDTOList = new ArrayList<>();
        for(ProductGroup productGroup : productGroupList) {
            ProductGroupDTO productGroupDTO = new ProductGroupDTO();
            productGroupDTO.setProductGroupId(productGroup.getId());
            productGroupDTO.setProductGroupName(productGroup.getProductGroupName());
            productGroupDTOList.add(productGroupDTO);
        }
        return productGroupDTOList;
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean addNewCategory(String productGroupName) {
        ProductGroup productGroup = new ProductGroup();
        productGroup.setProductGroupName(productGroupName);
        try{
            categoryRepository.save(productGroup);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateCategory(int productGroupId, String productGroupName) {
        ProductGroup productGroup = new ProductGroup();
        productGroup.setId(productGroupId);
        productGroup.setProductGroupName(productGroupName);
        try {
            categoryRepository.save(productGroup);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
