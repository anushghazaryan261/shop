package com.example.demo.rest.controller;

import com.example.demo.rest.model.category.CategoryRequestModel;
import com.example.demo.rest.model.category.CategoryResponseModel;
import com.example.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private static final Logger LOGGER= LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/category")
    public ResponseEntity<CategoryResponseModel> create(@RequestBody CategoryRequestModel requestModel) {
        LOGGER.info("Creating category " + requestModel);
        CategoryResponseModel categoryResponseModel = categoryService.create(requestModel);
        return ResponseEntity.ok(categoryResponseModel);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<CategoryResponseModel>> getAll() {
        LOGGER.info("Getting all categories");
        final List<CategoryResponseModel> categoryResponseModels = categoryService.getAll();
        if(categoryResponseModels!=null){
            LOGGER.info("Found successfully");
            return ResponseEntity.ok(categoryResponseModels);
        }else{
            LOGGER.info("Failed to get any category");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<CategoryResponseModel> getById(@PathVariable Long id) {
        LOGGER.info("Getting category by id " + id);
        final CategoryResponseModel responseModel = categoryService.getById(id);
        if(responseModel!=null){
            LOGGER.info("Found successfully");
            return ResponseEntity.ok(responseModel);
        }else{
            LOGGER.info("Failed to find the category");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping(value = "/category/{id}")
    public ResponseEntity<CategoryResponseModel> getById(@PathVariable Long id, @RequestBody CategoryRequestModel requestModel) {
        LOGGER.info("Requested to update category with id " + id + " and requestModel " + requestModel);
        if(getById(id)!=null){
            LOGGER.info("Updated successfully");
            final CategoryResponseModel responseModel = categoryService.update(id, requestModel);
            return ResponseEntity.ok(responseModel);
        }else{
            LOGGER.info("Failed to update");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @DeleteMapping(value = "/category/{id}")
    public void delete(@PathVariable Long id) {
        LOGGER.info("Requested to delete category with id " + id);
        if(getById(id)!=null){
            LOGGER.info("Successfully deleted");
            categoryService.delete(id);
        }else{
            LOGGER.info("Failed to delete");
        }

    }
}
