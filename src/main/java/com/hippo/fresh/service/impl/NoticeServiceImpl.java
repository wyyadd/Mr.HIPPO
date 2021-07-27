package com.hippo.fresh.service.impl;

import com.hippo.fresh.dao.NoticeRepository;
import com.hippo.fresh.entity.Notice;
import com.hippo.fresh.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    /**查找用户消息通知*/
    public Map<String,Object> find(Long userId) {
        Map<String, Object> map = new HashMap<>();
        List<Notice> notices = noticeRepository.findAllByUserId(userId);
        for (Notice notice : notices) {
            if (notice.getIsread().equals(0)) {
                map.put("messageId", notice.getId());
                map.put("message", notice.getMessage());
                map.put("productId", notice.getProductId());
                map.put("productPicture", notice.getProductPicture());
                map.put("oldPrice", notice.getOldPrice());
                map.put("currentPrice", notice.getCurrentPrice());
                map.put("percentage", notice.getPercentage());

                String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(notice.getCreateTime());
                map.put("createTime",createTime);

                //消息发送过一次之后改为已读
                notice.setIsread(1);
                noticeRepository.save(notice);
                return map;
            }
        }
        return map;
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
