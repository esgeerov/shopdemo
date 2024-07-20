package com.example.shopdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    String fullname;
    @CreationTimestamp
    Date createdAt;
    @ColumnDefault(value = "1")
    Integer active;

}
