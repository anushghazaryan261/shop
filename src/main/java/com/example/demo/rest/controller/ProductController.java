package com.example.demo.rest.controller;

import com.example.demo.rest.model.product.ProductRequestModel;
import com.example.demo.rest.model.product.ProductResponseModel;
import com.example.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private static final Logger LOGGER= LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ProductResponseModel> create(@RequestBody ProductRequestModel requestModel) {
        LOGGER.info("Creating product " + requestModel);
        final ProductResponseModel productResponseModel = productService.create(requestModel);
        return ResponseEntity.ok(productResponseModel);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<List<ProductResponseModel>> getAll() {
        LOGGER.info("Getting all products");
        final List<ProductResponseModel> productResponseModels = productService.getAll();
        if(productResponseModels!=null){
            LOGGER.info("Found successfully");
            return ResponseEntity.ok(productResponseModels);
        }else{
            LOGGER.info("Failed to get any product");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(value = "/product/category/{categoryId}")
    public ResponseEntity<List<ProductResponseModel>> getAllByCageId(@PathVariable Long categoryId) {
        LOGGER.info("Trying to get all products by category id " + categoryId);
        final List<ProductResponseModel> productResponseModels = productService.getAllByCategoryId(categoryId);
        if(productResponseModels!=null){
            LOGGER.info("Found successfully");
            return ResponseEntity.ok(productResponseModels);
        }else{
            LOGGER.info("Failed to get any product");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductResponseModel> getById(@PathVariable Long id) {
        LOGGER.info("Trying to get product by id " + id);
        final ProductResponseModel responseModel = productService.getById(id);
        if(responseModel!=null){
            LOGGER.info("Found successfully");
            return ResponseEntity.ok(responseModel);
        }else{
            LOGGER.info("Failed to find");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<ProductResponseModel> getById(@PathVariable Long id, @RequestBody ProductRequestModel requestModel) {
        LOGGER.info("Requested to update animal with id " + id + " requestModel " + requestModel);
        if(productService.getById(id)!=null){
            LOGGER.info("Updated successfully");
            final ProductResponseModel responseModel = productService.update(id, requestModel);
            return ResponseEntity.ok(responseModel);
        }else{
            LOGGER.info("Failed to update");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping(value = "/product/{id}")
    public void delete(@PathVariable Long id) {
        LOGGER.info("Requested to delete product with id " + id);
        if(productService.getById(id)!=null){
            LOGGER.info("Deleted successfully");
            productService.delete(id);
        }else{
            LOGGER.info("Failed to delete");
        }
    }
}
