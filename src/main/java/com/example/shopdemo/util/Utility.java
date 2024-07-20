package com.example.shopdemo.util;

import com.example.shopdemo.dto.request.ReqToken;
import com.example.shopdemo.entity.User;
import com.example.shopdemo.entity.UserToken;
import com.example.shopdemo.enums.EnumAviableStatus;
import com.example.shopdemo.exception.ExceptionConstants;
import com.example.shopdemo.exception.ShopException;
import com.example.shopdemo.repository.UserRepository;
import com.example.shopdemo.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor


public class Utility {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    public UserToken checkToken(String token, Long userId) {
        if (token == null || userId == null) {
            throw new ShopException(ExceptionConstants.INVALID_REQEUST_DATA, "Token or UserId is null");
        }
        User user = userRepository.findUserByIdAndActive(userId, EnumAviableStatus.ACTIVE.value);
        if (user == null) {
            throw new ShopException(ExceptionConstants.INVALID_USER, "User not found");
        }
        UserToken userToken = userTokenRepository.findUserTokenByUserAndToken(user, token);
        if (userToken == null) {
            throw new ShopException(ExceptionConstants.INVALID_TOKEN, "userToken not found");
        }
        if (userToken.getActive()==EnumAviableStatus.DEACTIVE.value){
            throw new ShopException(ExceptionConstants.TOKEN_EXPIRED,"INVALID TOKEN");
        }
        return userToken;
    }
}
