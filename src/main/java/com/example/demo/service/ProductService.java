package com.example.demo.service;

import com.example.demo.persistence.model.Product;
import com.example.demo.persistence.repository.ProductRepository;
import com.example.demo.rest.model.product.ProductRequestModel;
import com.example.demo.rest.model.product.ProductResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private static final Logger LOGGER= LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseModel create(ProductRequestModel productRequestModel) {
        LOGGER.info("Creating product");
        Product product = buildProduct(productRequestModel);
        Product save = productRepository.save(product);
        LOGGER.info("Successfully created a product");
        return buildProductResponseModel(save);
    }

    public ProductResponseModel getById(Long id) {
        LOGGER.info("Getting product with id " + id);
        Product product;
        if (productRepository.findById(id).isPresent()) {
            product = productRepository.findById(id).get();
            LOGGER.info("Successfully found the product");
            return buildProductResponseModel(product);
        } else {
            LOGGER.info("Product not found");
            return null;
        }
    }

    public List<ProductResponseModel> getAll(){
        LOGGER.info("Getting all products");
        List<Product> all = productRepository.findAll();
        return all.stream().map(this::buildProductResponseModel).collect(Collectors.toList());
    }

    public List<ProductResponseModel> getAllByCategoryId(Long id){
        List<ProductResponseModel> all = getAll();
        List<ProductResponseModel> allByCategory=new ArrayList<>();
        for (ProductResponseModel productResponseModel : all) {
            Product product = buildProduct(productResponseModel.getProductRequestModel());
            if (product.getCategory().getId().equals(id)) {
                allByCategory.add(buildProductResponseModel(product));
            }
        }
        return allByCategory;
    }

    public ProductResponseModel update(Long id,ProductRequestModel productRequestModel){
        LOGGER.info("Requesting to update the product with id " + id);
        if (getById(id)!=null) {
            Product product = buildProduct(productRequestModel);
            product.setName(productRequestModel.getName());
            product.setPrice(productRequestModel.getPrice());
            LOGGER.info("Successfully updated the product");
            return buildProductResponseModel(product);
        }else{
            LOGGER.info("Failed to update the product");
            return null;
        }
    }

    public void delete(Long id){
        LOGGER.info("Requesting to delete product with id " + id);
        if(getById(id)!=null){
            productRepository.deleteById(id);
            LOGGER.info("Successfully deleted product");
        }else{
            LOGGER.info("Failed to delete product");
        }
    }

    private Product buildProduct(ProductRequestModel productRequestModel) {
        Product product=new Product();
        product.setName(productRequestModel.getName());
        product.setPrice(productRequestModel.getPrice());
        return product;
    }

    private ProductResponseModel buildProductResponseModel(Product product) {
        ProductResponseModel productResponseModel=new ProductResponseModel();
        productResponseModel.setId(product.getId());
        productResponseModel.setName(product.getName());
        productResponseModel.setPrice(product.getPrice());
        return productResponseModel;
    }
}
