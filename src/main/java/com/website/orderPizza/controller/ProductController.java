package com.website.orderPizza.controller;

import com.website.orderPizza.entity.Products;
import com.website.orderPizza.payload.ProductRequest;
import com.website.orderPizza.payload.ResponseData;
import com.website.orderPizza.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    MenuServiceImp menuServiceImp;
    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewProduct(@RequestParam("file") MultipartFile file,
                                           @RequestParam String productName,
                                           @RequestParam String description,
                                           @RequestParam double unitPrice,
                                           @RequestParam int productGroupId) {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = menuServiceImp.addNewProduct(file, productName, description, unitPrice, productGroupId);
        if (isSuccess) {
            responseData.setStatusCode(200);
            responseData.setDescription("Add New Product Successfully");
        } else {
            responseData.setStatusCode(400);
            responseData.setDescription("Add New Product Failed");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Products> deleteById(@PathVariable(name = "id") Integer id) {
        menuServiceImp.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest) {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = menuServiceImp.updateProduct(productRequest);
        if (isSuccess) {
            responseData.setStatusCode(200);
            responseData.setDescription("Updated Successfully");
        } else {
            responseData.setStatusCode(400);
            responseData.setDescription("Failed to update product");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
