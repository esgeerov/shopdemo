package com.example.shopdemo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.sql.Date;

@Entity
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String username;
     String password;
     @ManyToOne
     UserType userType;
     @OneToOne
     UserDetails userDetails;
     @CreationTimestamp
     Date createdAt;
    @ColumnDefault(value = "1")
    Integer active;



}
