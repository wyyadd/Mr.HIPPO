package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    //用户注册接口
    @PostMapping("/register")
    public ResponseUtils register(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("email");
        String verCode = jsonObject.getString("verCode");
        Long verCodeId = jsonObject.getLong("verCodeId");
        return userService.register(username,password,email,verCode,verCodeId);
    }

    //用户主页接口
    @PostMapping("/information")

    public ResponseUtils information(HttpServletRequest request) {
        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        log.info(token);
        System.out.println(token==null);
        System.out.println(token);
        Long id = JWTTokenUtil.parseAccessToken(token).getId();
        System.out.println(id);
        return userService.information(id);
    }


    //用户修改密码接口
    @PostMapping("/password")
    public ResponseUtils password(@RequestBody String jsStr,HttpServletRequest request) {

        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        JSONObject jsonObject = JSON.parseObject(jsStr);
        String oldPassword = jsonObject.getString("oldPassword");
        String newPassword = jsonObject.getString("newPassword");

//        System.out.println(oldPassword);
//        System.out.println(newPassword);
        return userService.passwordModify(userId,oldPassword,newPassword);
    }


    //用户修改个人资料接口
    @PostMapping("/information/modify")
    public ResponseUtils informationModify(@RequestBody String jsStr,HttpServletRequest request) {

        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        JSONObject jsonObject = JSON.parseObject(jsStr);
        String username = jsonObject.getString("username");
        String phone = jsonObject.getString("phone");
        String email = jsonObject.getString("email");

        return userService.informationModify(userId,username,email,phone);
    }

}
