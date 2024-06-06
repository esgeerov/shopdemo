package com.example.shopdemo.service;

import com.example.shopdemo.dto.request.ReqCategory;
import com.example.shopdemo.dto.response.RespCategory;
import com.example.shopdemo.dto.response.RespProduct;
import com.example.shopdemo.dto.response.RespStatus;
import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.entity.Category;
import com.example.shopdemo.enums.EnumAviableStatus;
import com.example.shopdemo.exception.ExceptionConstants;
import com.example.shopdemo.exception.ShopException;
import com.example.shopdemo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override

    public Response addCategory(ReqCategory reqCategory) {
        Response<Category> response = new Response();
        Category category = new Category();
        category.setName(reqCategory.getName());
        categoryRepository.save(category);
        response.setRespStatus(new RespStatus(EnumAviableStatus.ACTIVE.value, "success!!"));
        return response;
    }

    @Override
    public Response getCategoryList() {
        Response response = new Response();
        List<RespCategory> respCategories = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAllByActive(EnumAviableStatus.ACTIVE.value);
        if (categoryList.isEmpty()) {
            throw new ShopException(ExceptionConstants.CATEGORY_NOT_FOUND, "Category not found ");
        }
        for (Category category : categoryList) {
            RespCategory respCategory = new RespCategory();
            respCategory.setId(category.getId());
            respCategory.setName(category.getName());
            respCategories.add(respCategory);
        }
        response.setT(respCategories);
        response.setRespStatus(new RespStatus(EnumAviableStatus.ACTIVE.value, "success"));
        return response;
    }
}