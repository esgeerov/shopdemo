package com.example.shopdemo.service.impl;

import com.example.shopdemo.dto.request.ReqLogin;
import com.example.shopdemo.dto.request.ReqToken;
import com.example.shopdemo.dto.response.RespStatus;
import com.example.shopdemo.dto.response.RespToken;
import com.example.shopdemo.dto.response.RespUser;
import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.entity.User;
import com.example.shopdemo.entity.UserToken;
import com.example.shopdemo.enums.EnumAviableStatus;
import com.example.shopdemo.exception.ExceptionConstants;
import com.example.shopdemo.exception.ShopException;
import com.example.shopdemo.repository.UserRepository;
import com.example.shopdemo.repository.UserTokenRepository;
import com.example.shopdemo.service.UserService;
import com.example.shopdemo.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final Utility utility;

    @Override
    public Response<RespUser> login(ReqLogin reqLogin) {
        Response<RespUser> response = new Response<>();
        RespUser respUser = new RespUser();
        try {
            String username = reqLogin.getUsername();
            String password = reqLogin.getPassword();
            if (username == null || password == null) {
                throw new ShopException(ExceptionConstants.INVALID_REQEUST_DATA, "INVALID Request data");
            }
            User user = userRepository.findUserByUsernameAndPasswordAndActive(username, password, EnumAviableStatus.ACTIVE.value);
            if (user == null) {
                throw new ShopException(ExceptionConstants.INVALID_USER, "Invalid User");
            }
            String token = UUID.randomUUID().toString();
            UserToken userToken = new UserToken();
            userToken.setToken(token);
            userToken.setUser(user);
            userTokenRepository.save(userToken);
            respUser.setFullName(user.getFullname());
            respUser.setUsername(user.getUsername());
            respUser.setRespToken(new RespToken(user.getId(), token));
            response.setRespStatus(RespStatus.getSuccesMessage());
            response.setT(respUser);

        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response logout(ReqToken reqToken) {
        Response response = new Response();
        try {
           UserToken userToken= utility.checkToken(reqToken.getToken(),reqToken.getUserId());
           userToken.setActive(EnumAviableStatus.DEACTIVE.value);
           userTokenRepository.save(userToken);
           response.setRespStatus(RespStatus.getSuccesMessage());

        } catch (ShopException shopException) {
            response.setRespStatus(new RespStatus(shopException.getCode(), shopException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }
}
