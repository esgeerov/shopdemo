package com.example.shopdemo.repository;

import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.entity.Category;
import com.example.shopdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByActive(Integer active);
    Category findAllByActiveAndId(Integer active ,Long categoryId);
    Category findCategoryByActiveAndProduct(Integer active, Product product);

}
