package com.example.shopdemo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;


@Data
@Entity
@Table(name = "product")
@DynamicInsert
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
     Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    @Column
     String name;
     Double price;
     String currency;
   @CreationTimestamp
     Date createdAt;
    @ColumnDefault(value = "1")
     Integer active;
}
