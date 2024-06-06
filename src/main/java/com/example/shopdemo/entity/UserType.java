package com.example.shopdemo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.sql.Date;

@Entity
@Table(name = "userType")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @CreationTimestamp
    Date createdAt;



}
