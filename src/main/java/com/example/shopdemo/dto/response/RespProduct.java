package com.example.shopdemo.dto.response;

import com.example.shopdemo.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class RespProduct {
    Long productId;
    String name;
    Double price;
    String currency;
    RespCategory respCategory;


}
