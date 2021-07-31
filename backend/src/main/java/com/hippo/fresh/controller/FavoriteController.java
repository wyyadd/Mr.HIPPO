package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.FavoriteRepository;
import com.hippo.fresh.dao.NoticeRepository;
import com.hippo.fresh.entity.Favorite;
import com.hippo.fresh.entity.Notice;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.FavoriteService;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private NoticeRepository noticeRepository;


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


//    //商品降价提醒
//    @Scheduled(fixedRate = 1000 * 60 * 30)
//    public void priceDropReminder() {
//        List<Favorite> favorites = favoriteRepository.findAll();
//        for(Favorite favorite:favorites){
//            if(!favoriteService.priceLowestReminder(favorite)){
//                if(!favoriteService.priceDrop30Reminder(favorite)){
//                    if(!favoriteService.priceDrop20Reminder(favorite)){
//                        favoriteService.priceDrop10Reminder(favorite);
//                    }
//                }
//            }
//        }
//    }

}
