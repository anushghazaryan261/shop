package com.example.demo.rest.model.product;

import java.io.Serializable;
import java.util.Objects;

public class ProductResponseModel implements Serializable {
    private static final long serialVersionUID = -2240818277495427928L;
    private Long id;
    private String name;
    private double price;
    private ProductRequestModel productRequestModel;

    public ProductResponseModel(Long id, String name, double price, ProductRequestModel productRequestModel) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productRequestModel = productRequestModel;
    }

    public ProductResponseModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductRequestModel getProductRequestModel() {
        return productRequestModel;
    }

    public void setProductRequestModel(ProductRequestModel productRequestModel) {
        this.productRequestModel = productRequestModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseModel that = (ProductResponseModel) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(productRequestModel, that.productRequestModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, productRequestModel);
    }

    @Override
    public String toString() {
        return "ProductResponseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productRequestModel=" + productRequestModel +
                '}';
    }
}
