package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Favorite;
import com.hippo.fresh.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    //根据用户id查找用户通知
    List<Notice> findAllByUserId(Long userId);

    //根据用户id和阅读状态查找用户通知
    List<Notice> findAllByUserIdAndIsread(Long userId,Integer isread);
}
