package com.example.shopdemo.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Data
@Table(name = "company")
@FieldDefaults(level = AccessLevel.PRIVATE)


public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "name")
    String name;
    @OneToMany
    List<Category> categoryList;
    @OneToMany
    List<Product> productList;
    @CreationTimestamp
    Date createdAt;
    @ColumnDefault(value = "1")
    Integer active;



}
