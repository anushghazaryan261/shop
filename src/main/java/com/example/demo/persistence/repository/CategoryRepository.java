package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
