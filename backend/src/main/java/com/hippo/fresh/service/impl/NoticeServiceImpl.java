package com.hippo.fresh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.kernel.util.JsonUtil;
import com.hippo.fresh.dao.NoticeRepository;
import com.hippo.fresh.entity.Notice;
import com.hippo.fresh.service.NoticeService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    /**查找用户消息通知*/
    public ResponseUtils find(Long userId) {
        JSONObject map = new JSONObject();
        List<Notice> notices = noticeRepository.findAllByUserIdAndIsread(userId, 0);
        if (notices.isEmpty()) {
            return ResponseUtils.response(201, "用户暂无通知", map);
        }
        for (Notice notice : notices) {
            map.put("messageId", notice.getId());
            map.put("message", notice.getMessage());
            map.put("productId", notice.getProductId());
            map.put("productName", notice.getProductName());
            map.put("productPicture", notice.getProductPicture());
            map.put("oldPrice", notice.getOldPrice());
            map.put("currentPrice", notice.getCurrentPrice());
            map.put("percentage", notice.getPercentage());

            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(notice.getCreateTime());
            map.put("createTime", createTime);

            //消息发送过一次之后改为已读
            notice.setIsread(1);
            noticeRepository.save(notice);
            return ResponseUtils.response(200, "用户通知获取成功", map);
        }
        return ResponseUtils.response(200, "用户通知获取成功", map);
    }


    /**修改消息通知阅读状态*/
    public boolean modifyReadStatus(Long noticeId){
        Optional<Notice> n = noticeRepository.findById(noticeId);
        if(n.isPresent()){
            Notice notice = n.get();
            notice.setIsread(1);
            noticeRepository.save(notice);
            return true;
        }
        else
            return false;
    }
}
