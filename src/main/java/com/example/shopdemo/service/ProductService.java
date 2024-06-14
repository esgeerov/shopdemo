package com.example.shopdemo.service;

import com.example.shopdemo.dto.request.ReqProduct;
import com.example.shopdemo.dto.response.RespProduct;
import com.example.shopdemo.dto.response.Response;

import java.util.List;

public interface ProductService {
    Response<List<RespProduct>> getProductList();

    Response addProduct(ReqProduct reqProduct);

    Response<RespProduct> getProductListById(Long productId);

    Response updateProduct(ReqProduct reqProduct);

    Response deleteProduct(Long productId);

    Response<List<RespProduct>> getProductListByCategoryId(Long categoryId);
}
