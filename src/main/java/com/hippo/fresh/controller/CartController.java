package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.CartRepository;
import com.hippo.fresh.entity.Cart;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.exception.UserHasExistException;
import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.CartService;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    private JSONObject jsonObject = new JSONObject();


//   //测试接口
//    @GetMapping("/test")
//    public ResponseUtils test(HttpServletRequest request) {
//
//        //从token中获取用户id
//        String token = request.getHeader(JWTConfig.tokenHeader);
//        Long userId = JWTTokenUtil.parseAccessToken(token).getId();
//
////        Optional<Cart> cart  = cartRepository.findByUserIdAndProductId(30L,5L);
//
////        List<Cart> cartlist =cartRepository.findByUserId(1L);
////
////        for(Cart c:cartlist){
////            cartRepository.delete(c);
////        }
//
////        List<Cart> carts =cartRepository.findByUserId(1L);
////
////        for(Cart c:carts){
////            System.out.println(c.getId());
////        }
//
////        if(cart.isPresent()){
////            System.out.println(cart.get().getId());
//////            int a = cartRepository.setProductQuantity(1L,666L);
////            cart.get().setQuantity(777L);
////            cartRepository.save(cart.get());
////        }
//
//        return ResponseUtils.response(200, "测试成功！！！", jsonObject);
//    }


    @PostMapping("/find")
    public ResponseUtils find(HttpServletRequest request) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        Optional<User> user = userService.findById(userId);
        if(user.isPresent()) {
            jsonObject.put("product", cartService.findCartInformationByUserId(userId));
            return ResponseUtils.response(200, "购物车商品信息查询成功", jsonObject);
        }
        else{
            jsonObject = new JSONObject();
            throw new UserNotExistException(null);
        }
    }


    @PostMapping("/update")
    public ResponseUtils update(@RequestBody HashMap<String,Object> map,HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        //解析products
        List<Map<String,Object>> products = (List<Map<String,Object>>)map.get("products");


        //先删除该用户所有原有购物车信息
       List<Cart> carts =cartRepository.findByUserId(userId);

        for(Cart c:carts){
            cartRepository.delete(c);
        }

        //更新该用户所有新的购物车信息
        for(Map<String,Object> product:products){
            Long productId = Long.valueOf(product.get("id").toString());
            Long productQuantity = Long.valueOf(product.get("quantity").toString());

            cartService.updateOne(userId,productId,productQuantity);//更新购物车中一件商品信息
        }

        return ResponseUtils.response(200, "购物车更新成功", jsonObject);
    }
}
