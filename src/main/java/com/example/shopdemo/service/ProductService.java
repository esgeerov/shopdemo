package com.example.shopdemo.service;

import com.example.shopdemo.dto.request.ReqProduct;
import com.example.shopdemo.dto.request.ReqToken;
import com.example.shopdemo.dto.response.RespProduct;
import com.example.shopdemo.dto.response.Response;

import java.util.List;

public interface ProductService {
    Response<List<RespProduct>> getProductList(ReqToken reqToken);

    Response addProduct(ReqProduct reqProduct);

    Response<RespProduct> getProductListById(ReqProduct reqProduct);

    Response updateProduct(ReqProduct reqProduct);

    Response deleteProduct(ReqProduct reqProduct);

    Response<List<RespProduct>> getProductListByCategoryId(Long categoryId);
}
