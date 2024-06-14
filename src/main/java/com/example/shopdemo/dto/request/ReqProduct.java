package com.example.shopdemo.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqProduct {

     String name;
     Double price;
     String currency;
     Long categoryId;
}
