package com.example.shopdemo.controller;

import com.example.shopdemo.dto.request.ReqLogin;
import com.example.shopdemo.dto.request.ReqToken;
import com.example.shopdemo.dto.request.ReqUser;
import com.example.shopdemo.dto.response.RespUser;
import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    public Response<RespUser> login(@RequestBody ReqLogin reqLogin){
        return userService.login(reqLogin);
    }
    @PostMapping("/logout")
    public Response logout(@RequestBody ReqToken reqToken){
        return userService.logout(reqToken);
    }
    @PostMapping("/createUser")
    public Response createUser(@RequestBody ReqUser reqUser){
       return userService.createUser(reqUser);
    }

}
