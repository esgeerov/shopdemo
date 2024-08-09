package com.example.shopdemo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;
import javax.persistence.*;


@Data
@Entity
@Table(name = "user_token")
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    String token;
    @CreationTimestamp
    Date createdAt;
    @ColumnDefault(value = "1")
    Integer active;

}
