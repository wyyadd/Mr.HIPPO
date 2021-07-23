package com.hippo.fresh.service.impl;

import com.hippo.fresh.dao.NoticeRepository;
import com.hippo.fresh.entity.Notice;
import com.hippo.fresh.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    /**查找用户消息通知*/
    public List<Map<String,Object>> find(Long userId){
        List<Map<String,Object>> res = new ArrayList<>();
        List<Notice> notices = noticeRepository.findAllByUserId(userId);
        for(Notice notice:notices){
            Map<String,Object> map = new HashMap<>();
            map.put("messageId",notice.getId());
            map.put("message",notice.getMessage());
            map.put("createTime",notice.getCreateTime());
            map.put("read",notice.getIsread());
            if(notice.getIsread() == 0)
                res.add(map);
        }
        return res;
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
