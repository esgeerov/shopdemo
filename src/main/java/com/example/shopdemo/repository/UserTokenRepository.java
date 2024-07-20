package com.example.shopdemo.repository;

import com.example.shopdemo.entity.User;
import com.example.shopdemo.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
    UserToken findUserTokenByUserAndToken(User user, String token);
}
