package com.example.shopdemo.service.impl;

import com.example.shopdemo.dto.request.ReqCategory;
import com.example.shopdemo.dto.response.RespCategory;
import com.example.shopdemo.dto.response.RespStatus;
import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.entity.Category;
import com.example.shopdemo.enums.EnumAviableStatus;
import com.example.shopdemo.exception.ExceptionConstants;
import com.example.shopdemo.exception.ShopException;
import com.example.shopdemo.repository.CategoryRepository;
import com.example.shopdemo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override

    public Response addCategory(ReqCategory reqCategory) {
        Response response = new Response();
        try {
            Category category = new Category();
            category.setName(reqCategory.getName());
            if (reqCategory == null) {
                throw new ShopException(ExceptionConstants.INVALID_REQEUST_DATA, "Data not found");
            }
            categoryRepository.save(category);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response<List<RespCategory>> getCategoryList() {
        Response<List<RespCategory>> response = new Response<>();
        List<RespCategory> respCategoryList = new ArrayList<>();
        try {
            List<Category> categoryList = categoryRepository.findAllByActive(EnumAviableStatus.ACTIVE.value);
            if (categoryList.isEmpty()) {
                throw new ShopException(ExceptionConstants.CATEGORY_NOT_FOUND, "Category not found ");
            }
//            for (Category category : categoryList) {
//                RespCategory respCategory = new RespCategory();
//                respCategory.setId(category.getId());
//                respCategory.setName(category.getName());
//                }
            respCategoryList = categoryList.stream().map(category -> mapping(category)).collect(Collectors.toList());
            response.setT(respCategoryList);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response<RespCategory> getCategoryListByid(Long categoryId) {
        Response<RespCategory> response = new Response<>();
        try {
            if (categoryId == null) {
                throw new ShopException(ExceptionConstants.CATEGORYID_NOT_FOUND, "categoryId not found");
            }
            Category category = categoryRepository.findAllByActiveAndId(EnumAviableStatus.ACTIVE.value, categoryId);
            if (category == null) {
                throw new ShopException(ExceptionConstants.CATEGORY_NOT_FOUND, "Category not found");
            }
            RespCategory respCategory = mapping(category);
            response.setT(respCategory);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;

    }

    @Override
    public Response updateCategory(ReqCategory reqCategory) {
        Response response = new Response();
        try {
            Long categoryId = reqCategory.getCategoryId();
            String name = reqCategory.getName();
            if (categoryId == null || name == null) {
                throw new ShopException(ExceptionConstants.CATEGORY_NOT_FOUND, "Category not found");
            }
            Category category = categoryRepository.findAllByActiveAndId(EnumAviableStatus.ACTIVE.value, categoryId);
            if (category == null) {
                throw new ShopException(ExceptionConstants.CATEGORY_NOT_FOUND, "Category not found");
            }
            category.setName(reqCategory.getName());
            categoryRepository.save(category);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response deleteCategory(Long categoryId) {
        Response response = new Response();
        try {
            if (categoryId == null) {
                throw new ShopException(ExceptionConstants.CATEGORYID_NOT_FOUND, "Category id not found");
            }
            Category category = categoryRepository.findAllByActiveAndId(EnumAviableStatus.ACTIVE.value, categoryId);
            if (category == null) {
                throw new ShopException(ExceptionConstants.CATEGORY_NOT_FOUND, "Category not found");
            }
            category.setActive(EnumAviableStatus.DEACTIVE.value);
            categoryRepository.save(category);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    public RespCategory mapping(Category category) {
        return RespCategory.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}