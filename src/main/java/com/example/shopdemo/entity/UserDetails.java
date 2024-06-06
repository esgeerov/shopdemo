package com.example.shopdemo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;


@Entity
@Table(name = "userDetails")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    @JoinColumn(name = "userId")
    User user;
    String fin;
    String firstName;
    String lastName;
    String email;
    String phone;
    Integer age;
    @CreationTimestamp
    Date createdAt;
    @ColumnDefault(value = "1")
    Integer active;
}

