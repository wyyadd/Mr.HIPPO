package com.hippo.fresh.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.ReceiverRepository;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.ReceiverService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/receiver")
public class ReceiverController {

    @Autowired
    private ReceiverService receiverService;

    //增加收件人
    @PostMapping("/add")
    public ResponseUtils addReceiver(@RequestBody String jsStr, HttpServletRequest request) {

        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long id = JWTTokenUtil.parseAccessToken(token).getId();

        JSONObject jsonObject = JSON.parseObject(jsStr);
        String name = jsonObject.getString("name");
        String phone = jsonObject.getString("phone");
        String province = jsonObject.getString("province");
        String city = jsonObject.getString("city");
        String district = jsonObject.getString("district");
        String address = jsonObject.getString("address");

       return  receiverService.save(id,name,phone,province,city,district,address);
    }

    //删除收件人
    @PostMapping("/delete")
    public ResponseUtils deleteReceiver(@RequestBody String jsStr, HttpServletRequest request) {

        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        JSONObject jsonObject = JSON.parseObject(jsStr);
        Long receiverId = jsonObject.getLong("receiverId");

        return  receiverService.delete(userId,receiverId);
    }


}
