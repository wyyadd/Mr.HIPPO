package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSON;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

//    //用户注册接口
    @PostMapping("/register")
    public ResponseUtils register(@RequestBody String jsonObject) {
        User user = JSON.parseObject(jsonObject, User.class);
        return userService.register(user.getUsername(), user.getPassword(), user.getEmail());
    }

//    @PostMapping("/register")
//    public Map<String,Object> register(@RequestBody HashMap<String,Object> map) {
//        String username = (String) map.get("username");
//        String password = (String) map.get("password");
//        String email = (String) map.get("email");
//        return userService.register(username, password, email);
//    }

    //用户主页接口
    @GetMapping("/information")
    public Map<String,Object> information(HttpServletRequest request) {
        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long id = JWTTokenUtil.parseAccessToken(token).getId();
        return userService.information(id);
    }

}
