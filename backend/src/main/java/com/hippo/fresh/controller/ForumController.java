package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hippo.fresh.dao.ForumRepository;
import com.hippo.fresh.entity.Forum;
import com.hippo.fresh.exception.ForumNotExistException;
import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.ForumService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @Autowired
    private ForumRepository forumRepository;

    //发帖
    @PostMapping("/add")
    public ResponseUtils add(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        String content = jsonObject.getString("content");
        String title = jsonObject.getString("title");

        return  forumService.save(userId,title,content);
    }


    //查询所有帖子信息
    @PostMapping("/findall")
    public ResponseUtils GetAllForum(@RequestBody JSONObject jsonObject) {

        int pageIndex = jsonObject.getInteger("pageIndex"); //当前页码（注意：第一页是从0开始）
        int pageSize = jsonObject.getInteger("pageSize"); //分页大小


//        List<String> sortProperties = new ArrayList<>();
//        sortProperties.add("id");
//        Sort sort = new Sort(Sort.Direction.DESC,sortProperties);
        Sort sort = Sort.by(Sort.Order.desc("createTime"));

      // 分页排序
      Pageable pageable = PageRequest.of(pageIndex, pageSize,sort);

      Page<Forum> forums= forumRepository.findAll(pageable);

        JSONObject res = new JSONObject();
        res.put("forum",forums.getContent());
        return ResponseUtils.response(200,"论坛获取成功",res);

    }

    //根据帖子id查询帖信息
    @PostMapping("/find")
    public ResponseUtils FindForum(@RequestBody JSONObject jsonObject){
        Long forumId = jsonObject.getLong("forumId");
        if(forumRepository.existsById(forumId)){
            return forumService.GetById(forumId);
        }else {
            throw new ForumNotExistException(forumId);
        }
    }

}
