package com.example.shopdemo.service;

import com.example.shopdemo.dto.request.ReqProduct;
import com.example.shopdemo.dto.response.RespProduct;
import com.example.shopdemo.dto.response.Response;

import java.util.List;

public interface ProductService {
    Response getProductList();

    Response addProduct(ReqProduct reqProduct);
}
