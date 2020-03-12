package com.example.demo.service;

import com.example.demo.persistence.model.Category;
import com.example.demo.persistence.repository.CategoryRepository;
import com.example.demo.rest.model.category.CategoryRequestModel;
import com.example.demo.rest.model.category.CategoryResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseModel create(CategoryRequestModel categoryRequestModel) {
        LOGGER.info("Creating category");
        Category category = buildCategory(categoryRequestModel);
        Category save = categoryRepository.save(category);
        LOGGER.info("Successfully created a Category");
        return buildCategoryResponseModel(save);
    }

    public CategoryResponseModel getById(Long id) {
        LOGGER.info("Getting Category with id " + id);
        Category category;
        if (categoryRepository.findById(id).isPresent()) {
            category = categoryRepository.findById(id).get();
            LOGGER.info("Successfully found the category");
            return buildCategoryResponseModel(category);
        } else {
            LOGGER.info("Category not found");
            return null;
        }
    }

    public List<CategoryResponseModel> getAll(){
        LOGGER.info("Getting all categories");
        List<Category> all = categoryRepository.findAll();
        return all.stream().map(this::buildCategoryResponseModel).collect(Collectors.toList());
    }

    public CategoryResponseModel update(Long id,CategoryRequestModel categoryRequestModel){
        LOGGER.info("Requesting to update the category with id " + id);
        if (getById(id)!=null) {
            Category category = buildCategory(categoryRequestModel);
            category.setName(categoryRequestModel.getName());
            LOGGER.info("Successfully updated the category");
            return buildCategoryResponseModel(category);
        }else{
            LOGGER.info("Failed to update the category");
            return null;
        }
    }

    public void delete(Long id){
        LOGGER.info("Requesting to delete category with id " + id);
        if(getById(id)!=null){
            categoryRepository.deleteById(id);
            LOGGER.info("Successfully deleted category");
        }else{
            LOGGER.info("Failed to delete category");
        }
    }

    private Category buildCategory(CategoryRequestModel categoryRequestModel) {
        Category category = new Category();
        category.setName(categoryRequestModel.getName());
        return category;
    }

    private CategoryResponseModel buildCategoryResponseModel(Category category) {
        CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
        categoryResponseModel.setId(category.getId());
        categoryResponseModel.setName(category.getName());
        return categoryResponseModel;
    }
}
