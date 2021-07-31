package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.exception.UserHasExistException;
import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.NoticeService;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/message")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;

    //获取用户通知接口
    @PostMapping("/find")
    public ResponseUtils find(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        if(userService.findById(userId).isPresent()) {
            return noticeService.find(userId);
        }
        else{
            throw new UserNotExistException(jsonObject);
        }
    }


    //更改消息状态接口
    @PostMapping("/read")
    public ResponseUtils read(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        Long messageId = jsonObject.getLong("messageId");

        if(userService.findById(userId).isPresent()) {
            if(noticeService.modifyReadStatus(messageId))
                return ResponseUtils.response(200, "用户通知阅读状态修改成功", jsonObject);
            else
                return ResponseUtils.response(404, "用户通知阅读状态修改失败", jsonObject);
        }
        else{
            throw new UserNotExistException(jsonObject);
        }
    }




}
