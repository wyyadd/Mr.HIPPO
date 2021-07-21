package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.FavoriteService;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;


    //收藏夹添加商品接口
    @PostMapping("/add")
    public ResponseUtils add(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        Long productId = jsonObject.getLong("productId");
        return favoriteService.save(userId, productId);
    }

    //收藏夹删除商品接口
    @PostMapping("/delete")
    public ResponseUtils delete(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        Long productId = jsonObject.getLong("productId");
        return favoriteService.delete(userId, productId);
    }

    //收藏夹查找商品接口
    @PostMapping("/find")
    public ResponseUtils find(HttpServletRequest request) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        Optional<User> user = userService.findById(userId);
        if(user.isPresent()) {
            return favoriteService.find(userId);
        }
        else{
            JSONObject jsonObject = new JSONObject();
            throw new UserNotExistException(null);
        }
    }
}
