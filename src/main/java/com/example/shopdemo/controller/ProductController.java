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
@RequestMapping(name ="/product")


public class ProductController {
    private final ProductService productService;
    @GetMapping("/GetProductList")

    public Response getProductList(){
        return productService.getProductList();

    }
    @PostMapping("/addProduct")
    public Response addProduct(@RequestBody ReqProduct reqProduct){
        return productService.addProduct(reqProduct);
    }


}
