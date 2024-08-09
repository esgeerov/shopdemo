package com.example.shopdemo.service;

import com.example.shopdemo.dto.request.ReqLogin;
import com.example.shopdemo.dto.request.ReqToken;
import com.example.shopdemo.dto.request.ReqUser;
import com.example.shopdemo.dto.response.RespUser;
import com.example.shopdemo.dto.response.Response;
import org.springframework.stereotype.Service;



public interface UserService {
   Response<RespUser> login(ReqLogin reqLogin);

   Response logout(ReqToken reqToken);

    Response createUser(ReqUser reqUser);
}
