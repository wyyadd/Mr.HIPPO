package com.hippo.fresh.controller;

import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/find")
    public Map<String,Object> test(HttpServletRequest request) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        Map<String, Object> res = new HashMap<>();
        res.put("product",cartService.findCartInformationByUserId(userId));

        return res;
    }
}
