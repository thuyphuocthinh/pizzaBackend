package com.website.orderPizza.controller;

import com.website.orderPizza.DTO.ProductGroupDTO;
import com.website.orderPizza.payload.ResponseData;
import com.website.orderPizza.payload.request.CategoryRequest;
import com.website.orderPizza.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryServiceImp;
    @GetMapping("/get")
    public ResponseEntity<?> getListOfCategory() {
        ResponseData responseData = new ResponseData();
        List<ProductGroupDTO> productGroupDTOList = categoryServiceImp.getListOfCategory();
        if (!productGroupDTOList.isEmpty()){
            responseData.setStatusCode(200);
            responseData.setData(productGroupDTOList);
            responseData.setDescription("Get Category Successfully");
        } else {
            responseData.setStatusCode(204);
            responseData.setDescription("No content");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {
        categoryServiceImp.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCategory(@RequestBody CategoryRequest categoryRequest) {
        ResponseData responseData = new ResponseData();
        if (categoryServiceImp.addNewCategory(categoryRequest.getProductGroupName())) {
            responseData.setDescription("Added a new category successfully");
            responseData.setStatusCode(200);
        } else {
            responseData.setDescription("Added a new category failed");
            responseData.setStatusCode(400);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        ResponseData responseData = new ResponseData();
        if (categoryServiceImp.updateCategory(categoryRequest.getProductGroupId(), categoryRequest.getProductGroupName())) {
            responseData.setDescription("Updated category successfully");
            responseData.setStatusCode(200);
        } else {
            responseData.setDescription("Updated category failed");
            responseData.setStatusCode(400);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
