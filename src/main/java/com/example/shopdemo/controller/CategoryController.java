package com.example.shopdemo.controller;

import com.example.shopdemo.dto.request.ReqCategory;
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
    public Response getCategoryList(){
        return categoryService.getCategoryList();
    }
}
