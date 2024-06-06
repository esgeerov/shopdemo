package com.example.shopdemo.service;

import com.example.shopdemo.dto.request.ReqProduct;
import com.example.shopdemo.dto.response.RespProduct;
import com.example.shopdemo.dto.response.RespStatus;
import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.entity.Product;
import com.example.shopdemo.enums.EnumAviableStatus;
import com.example.shopdemo.exception.ExceptionConstants;
import com.example.shopdemo.exception.ShopException;
import com.example.shopdemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements  ProductService{
    private final ProductRepository productRepository;
    @Override
    public Response getProductList() {
        Response response=new Response();
        List<RespProduct> respProductList=new ArrayList<>();
        try {
            List<Product> productList= productRepository.findAllByActive(EnumAviableStatus.ACTIVE.value);
            if (productList.isEmpty()){
                throw new ShopException(ExceptionConstants.PRODUCT_NOT_FOUND,"Product not found");
            }
            for (Product product:productList){
                RespProduct respProduct=new RespProduct();
                respProduct.setId(product.getId());
                respProduct.setName(product.getName());
                respProduct.setPrice(product.getPrice());
                respProductList.add(respProduct);
            }
            response.setT(respProductList);
            response.setRespStatus(new RespStatus(EnumAviableStatus.ACTIVE.value,"success"));
        }catch (ShopException e){
         e.printStackTrace();
        }catch  (Exception e){
            e.printStackTrace();

        }
        return response;
    }

    @Override
    public Response addProduct(ReqProduct reqProduct) {
        RespProduct respProduct=new RespProduct();
        Response<RespProduct> response=new Response();
        Product product=new Product();
        product.setName(reqProduct.getName());
        product.setPrice(reqProduct.getPrice());
        productRepository.save(product);
        response.setRespStatus(new RespStatus(EnumAviableStatus.ACTIVE.value, "success"));
        return response;
    }
}
