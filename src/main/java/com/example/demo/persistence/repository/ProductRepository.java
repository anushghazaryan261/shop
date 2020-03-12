package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    int countByCategoryId(Long id);
}
