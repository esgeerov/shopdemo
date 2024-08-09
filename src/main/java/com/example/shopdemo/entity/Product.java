package com.example.shopdemo.entity;


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
import javax.persistence.*;

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
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    Category category;
    @Column(name = "name")
    String name;
    @Column(name = "price")
    Double price;
    @Column(name = "currency")
    String currency;
    @CreationTimestamp
    Date createdAt;
    @ColumnDefault(value = "1")
    Integer active;
}
