package com.hippo.fresh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.ForumRepository;
import com.hippo.fresh.dao.UserRepository;
import com.hippo.fresh.entity.Forum;
import com.hippo.fresh.service.ForumService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private UserRepository userRepository;

    //发帖
    public ResponseUtils save(Long userId,String title, String content){

        String username = userRepository.findById(userId).get().getUsername();
        String avatar = userRepository.findById(userId).get().getAvatar();
        forumRepository.save(new Forum(userId,username,avatar,title,content,new Timestamp(System.currentTimeMillis())));
        JSONObject jsonObject = new JSONObject();
        return ResponseUtils.response(200,"发帖成功",jsonObject);

    }

}
