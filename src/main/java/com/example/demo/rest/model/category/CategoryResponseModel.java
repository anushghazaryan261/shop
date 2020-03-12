package com.example.demo.rest.model.category;

import java.io.Serializable;
import java.util.Objects;

public class CategoryResponseModel implements Serializable {

    private static final long serialVersionUID = -3873259089816781656L;

    private Long id;
    private String name;
    private CategoryResponseModel categoryResponseModel;
    private int productsCountInThisCategory;

    public CategoryResponseModel() {
    }

    public CategoryResponseModel(Long id, String name, CategoryResponseModel categoryResponseModel) {
        this.id = id;
        this.name = name;
        this.categoryResponseModel = categoryResponseModel;
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

    public CategoryResponseModel getCategoryResponseModel() {
        return categoryResponseModel;
    }

    public void setCategoryResponseModel(CategoryResponseModel categoryResponseModel) {
        this.categoryResponseModel = categoryResponseModel;
    }

    public int getProductsCountInThisCategory() {
        return productsCountInThisCategory;
    }

    public void setProductsCountInThisCategory(int productsCountInThisCategory) {
        this.productsCountInThisCategory = productsCountInThisCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryResponseModel that = (CategoryResponseModel) o;
        return productsCountInThisCategory == that.productsCountInThisCategory &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(categoryResponseModel, that.categoryResponseModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryResponseModel, productsCountInThisCategory);
    }

    @Override
    public String toString() {
        return "CategoryResponseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryResponseModel=" + categoryResponseModel +
                ", productsCountInThisCategory=" + productsCountInThisCategory +
                '}';
    }
}
