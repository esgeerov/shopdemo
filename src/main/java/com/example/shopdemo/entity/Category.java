package com.example.shopdemo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name= "category")
@DynamicInsert
@FieldDefaults(level = AccessLevel.PRIVATE)


public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
    @OneToMany
    @JoinColumn(name = "product_id")
    List <Product> product;
    @Column
     String name;
    @CreationTimestamp
    Date createdAt;
    @ColumnDefault(value = "1")
     Integer active;

}
