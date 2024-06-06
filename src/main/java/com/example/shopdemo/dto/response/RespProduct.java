package com.example.shopdemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RespProduct {
    private Long id;
    private String name;
    private Double price;

}
