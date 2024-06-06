package com.example.shopdemo.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum EnumAviableStatus {
  ACTIVE(1), DEACTIVE(0);
    public int value;
}
