package com.example.shopdemo.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ReqUser {
    String username;
    String password;
    String fullName;


}
