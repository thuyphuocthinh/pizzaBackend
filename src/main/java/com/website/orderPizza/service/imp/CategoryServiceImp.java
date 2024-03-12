package com.website.orderPizza.service.imp;

import com.website.orderPizza.DTO.ProductGroupDTO;

import java.util.List;

public interface CategoryServiceImp {
    List<ProductGroupDTO> getListOfCategory();
    void deleteById(Integer id);
}
