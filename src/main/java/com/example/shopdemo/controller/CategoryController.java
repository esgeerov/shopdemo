package com.example.shopdemo.controller;

import com.example.shopdemo.dto.request.ReqCategory;
import com.example.shopdemo.dto.response.RespCategory;
import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public Response addCategory(@RequestBody ReqCategory reqCategory) {
        return categoryService.addCategory(reqCategory);
    }

    @GetMapping("/getCategoryList")
    public Response<List<RespCategory>> getCategoryList(){
        return categoryService.getCategoryList();
    }
    @GetMapping("/getCategoryListById")
    public Response<RespCategory> getCategoryListById(@RequestParam(value = "id") Long categoryId){
        return categoryService.getCategoryListByid(categoryId);
    }
    @PostMapping("/updateCategory")
    public Response updateCategory(@RequestBody ReqCategory reqCategory){
        return categoryService.updateCategory(reqCategory);
    }
    @PostMapping("/deleteCategory/{categoryId}")
    public Response deleteCategory(@PathVariable Long categoryId ){
        return categoryService.deleteCategory(categoryId);
    }
    @GetMapping("/getCategoryByProductId/{productId}")
    public Response<RespCategory> getCategoryByProductId(@PathVariable Long productId){
        return categoryService.getCategoryByProductId(productId);
    }

}
