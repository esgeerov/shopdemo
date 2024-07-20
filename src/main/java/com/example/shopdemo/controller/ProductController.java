package com.example.shopdemo.controller;

import com.example.shopdemo.dto.request.ReqProduct;
import com.example.shopdemo.dto.request.ReqToken;
import com.example.shopdemo.dto.response.RespProduct;
import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")


public class ProductController {
    private final ProductService productService;

    @PostMapping("/GetProductList")

    public Response<List<RespProduct>> getProductList(@RequestBody ReqToken reqToken) {
        return productService.getProductList(reqToken);

    }

    @PostMapping("/addProduct")
    public Response addProduct(@RequestBody ReqProduct reqProduct) {
        return productService.addProduct(reqProduct);
    }

    @PostMapping("/getProductListById")
    public Response<RespProduct> getProductListById(@RequestBody ReqProduct reqProduct) {
        return productService.getProductListById(reqProduct);
    }

    @PostMapping("/updateProduct")
    public Response updateProduct(@RequestBody ReqProduct reqProduct) {
        return productService.updateProduct(reqProduct);
    }

    @PostMapping("/deleteProduct")
    public Response deleteProduct(@RequestBody ReqProduct reqProduct) {
        return productService.deleteProduct(reqProduct);
    }

    @GetMapping("/getProductListByCategoryId/{id}")
    public Response<List<RespProduct>> getProductListByCategoryId(@PathVariable(value = "id") Long categoryId) {
        return productService.getProductListByCategoryId(categoryId);
    }


}
