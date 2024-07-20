package com.example.shopdemo.service;

import com.example.shopdemo.dto.request.ReqCategory;
import com.example.shopdemo.dto.response.RespCategory;
import com.example.shopdemo.dto.response.Response;
import java.util.List;

public interface CategoryService {
    Response addCategory(ReqCategory reqCategory);

    Response<List<RespCategory>> getCategoryList();

    Response<RespCategory> getCategoryListByid(Long categoryId);

    Response updateCategory(ReqCategory reqCategory);

    Response deleteCategory(Long categoryId);

    Response<RespCategory> getCategoryByProductId(Long productId);
}
