package com.example.shopdemo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShopException extends RuntimeException{
    private Integer code;
    public ShopException(Integer code,String message){
        super(message);
        this.code=code;
    }
    public Integer getCode(){
        return code;
    }
}
