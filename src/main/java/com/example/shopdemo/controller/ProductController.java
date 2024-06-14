package com.example.shopdemo.controller;

import com.example.shopdemo.dto.request.ReqProduct;
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
    @GetMapping("/GetProductList")

    public Response<List<RespProduct>> getProductList(){
        return productService.getProductList();

    }
    @PostMapping("/addProduct")
    public Response addProduct(@RequestBody ReqProduct reqProduct){
        return productService.addProduct(reqProduct);
    }

    @GetMapping("/getProductListById")
    public Response<RespProduct> getProductListById(@RequestParam(value = "id") Long productId){
        return productService.getProductListById(productId);
    }
    @PostMapping("/updateProduct")
    public Response updateProduct(@RequestBody ReqProduct reqProduct){
        return productService.updateProduct(reqProduct);
    }
    @PostMapping("/deleteProduct/{productId}")
    public Response deleteProduct(@PathVariable Long productId){
        return productService.deleteProduct(productId);
    }
    @GetMapping("/getProductListByCategoryId/{categoryId}")
    public Response<List<RespProduct>> getProductListByCategoryId(@PathVariable Long categoryId){
        return productService.getProductListByCategoryId(categoryId);
    }




}
