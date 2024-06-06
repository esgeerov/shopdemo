package com.example.shopdemo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShopException extends RuntimeException{
    public ShopException(Integer code,String message){
        super(message);
    }
}
