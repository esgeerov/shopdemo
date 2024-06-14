package com.example.shopdemo.service.impl;

import com.example.shopdemo.dto.request.ReqProduct;
import com.example.shopdemo.dto.response.RespCategory;
import com.example.shopdemo.dto.response.RespProduct;
import com.example.shopdemo.dto.response.RespStatus;
import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.entity.Category;
import com.example.shopdemo.entity.Product;
import com.example.shopdemo.enums.EnumAviableStatus;
import com.example.shopdemo.exception.ExceptionConstants;
import com.example.shopdemo.exception.ShopException;
import com.example.shopdemo.repository.CategoryRepository;
import com.example.shopdemo.repository.ProductRepository;
import com.example.shopdemo.service.CategoryService;
import com.example.shopdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Response<List<RespProduct>> getProductList() {
        Response<List<RespProduct>> response = new Response<>();
        List<RespProduct> respProductList = new ArrayList<>();
        try {
            List<Product> productList = productRepository.findAllByActive(EnumAviableStatus.ACTIVE.value);
            if (productList.isEmpty()) {
                throw new ShopException(ExceptionConstants.PRODUCT_NOT_FOUND, "Product not found");
            }
            for (Product product : productList) {
                RespProduct respProduct = mapping(product);
                respProductList.add(respProduct);
            }
            productList.stream().map(product -> mapping(product)).collect(Collectors.toList());
            response.setT(respProductList);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response addProduct(ReqProduct reqProduct) {
        RespProduct respProduct = new RespProduct();
        Response<RespProduct> response = new Response();
        Product product = new Product();
        try {
            if (reqProduct == null) {
                throw new ShopException(ExceptionConstants.INVALID_REQEUST_DATA, "Data not found");
            }
            Long categoryId=reqProduct.getCategoryId();
            Category category=categoryRepository.findAllByActiveAndId(EnumAviableStatus.ACTIVE.value,categoryId);
            product.setName(reqProduct.getName());
            product.setPrice(reqProduct.getPrice());
            product.setCurrency(reqProduct.getCurrency());
            product.setCategory(category);
            productRepository.save(product);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response<RespProduct> getProductListById(Long productId) {
        Response<RespProduct> response = new Response<>();
        try {
            if (productId == null) {
                throw new ShopException(ExceptionConstants.PRODUCTID_NOT_FOUND, "Product id not found ");
            }
            Product product = productRepository.findAllByActiveAndId(EnumAviableStatus.ACTIVE.value, productId);
            if (product == null) {
                throw new ShopException(ExceptionConstants.PRODUCT_NOT_FOUND, "Product not found");
            }
            RespProduct respProduct = mapping(product);
            response.setT(respProduct);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }

        return response;
    }

    @Override
    public Response updateProduct(ReqProduct reqProduct) {
        Response response = new Response();
        try {
            if (reqProduct == null) {
                throw new ShopException(ExceptionConstants.PRODUCT_NOT_FOUND, "Product not found");
            }
            Product product = new Product();
            product.setName(reqProduct.getName());
            product.setPrice(reqProduct.getPrice());
            productRepository.save(product);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response deleteProduct(Long productId) {
        Response response = new Response();
        try {
            if (productId == null) {
                throw new ShopException(ExceptionConstants.PRODUCTID_NOT_FOUND, "Product id not found");
            }
            Product product = productRepository.findAllByActiveAndId(EnumAviableStatus.ACTIVE.value, productId);
            product.setActive(EnumAviableStatus.DEACTIVE.value);
            productRepository.save(product);
            response.setRespStatus(RespStatus.getSuccesMessage());

        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response<List<RespProduct>> getProductListByCategoryId(Long categoryId) {
        Response<List<RespProduct>> response = new Response<>();
        try {
            if (categoryId == null) {
                throw new ShopException(ExceptionConstants.INVALID_REQEUST_DATA, "Enter id please");
            }
            Category category = categoryRepository.findAllByActiveAndId(EnumAviableStatus.ACTIVE.value, categoryId);
            if (category == null) {
                throw new ShopException(ExceptionConstants.CATEGORY_NOT_FOUND, "Category not found");
            }
            List<Product> productList = productRepository.findAllByActiveAndCategory(EnumAviableStatus.ACTIVE.value, category);
            if (productList.isEmpty()) {
                throw new ShopException(ExceptionConstants.PRODUCT_NOT_FOUND, "Product not found");
            }
           List<RespProduct> respProductList=productList.stream().map(this::mapping).collect(Collectors.toList());
                response.setT(respProductList);
                response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    private RespProduct mapping(Product product) {
        RespCategory respCategory=RespCategory.builder()
                .id(product.getCategory().getId())
                .name(product.getCategory().getName())
                .build();
        return RespProduct.builder()
                .productId(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .respCategory(respCategory)
                .build();
    }
}
