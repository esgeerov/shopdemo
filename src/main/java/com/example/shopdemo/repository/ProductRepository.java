package com.example.shopdemo.repository;

import com.example.shopdemo.entity.Category;
import com.example.shopdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByActive(Integer active);

    Product findAllByActiveAndId(Integer active, Long id);

    List<Product>findAllByActiveAndCategory(Integer active, Category category);
}
