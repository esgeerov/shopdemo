package com.example.shopdemo.service;

import com.example.shopdemo.dto.request.ReqCategory;
import com.example.shopdemo.dto.response.Response;

public interface CategoryService {
    Response addCategory(ReqCategory reqCategory);

    Response getCategoryList();
}
