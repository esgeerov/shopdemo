package com.example.shopdemo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Response<T> {
    @JsonProperty(value = "response")
    private T t;
    private RespStatus respStatus;
}
