package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.CartRepository;
import com.hippo.fresh.entity.Cart;
import com.hippo.fresh.entity.Favorite;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.exception.UserHasExistException;
import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.CartService;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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


    @PostMapping("/find")
    public ResponseUtils find(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        Optional<User> user = userService.findById(userId);
        if(user.isPresent()) {
            jsonObject.put("products", cartService.findCartInformationByUserId(userId));
            return ResponseUtils.response(200, "购物车商品信息查询成功", jsonObject);
        }
        else{
            throw new UserNotExistException(jsonObject);
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

        //先删除该用户新购物车中不存在的商品
       List<Cart> carts =cartRepository.findByUserId(userId);
        for(Cart cart:carts){
            int number = 0;
            for(Map<String,Object> product:products){
                Long productId = Long.valueOf(product.get("id").toString());

                if(cart.getProductId().equals(productId))
                    break;
                else
                    number++;

                if(number == products.size())
                    cartRepository.delete(cart);
            }
        }

        //更新该用户所有新的购物车信息
        for(Map<String,Object> product:products){
            Long productId = Long.valueOf(product.get("id").toString());
            Long productQuantity = Long.valueOf(product.get("quantity").toString());

            cartService.updateOne(userId,productId,productQuantity);//更新购物车中一件商品信息
        }

        return ResponseUtils.response(200, "购物车更新成功", jsonObject);
    }

    //商品降价提醒
//    @Scheduled(fixedRate = 1000 * 60 * 30)
    @Scheduled(fixedRate = 5000 )
    public void priceDropReminder() {
        List<Cart> carts = cartRepository.findAll();
        for(Cart cart:carts){
            if(!cartService.priceLowestReminder(cart)){
                if(!cartService.priceDrop30Reminder(cart)){
                    if(!cartService.priceDrop20Reminder(cart)){
                        cartService.priceDrop10Reminder(cart);
                    }
                }
            }
        }
    }
}
