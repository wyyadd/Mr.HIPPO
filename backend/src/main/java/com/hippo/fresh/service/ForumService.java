package com.hippo.fresh.service;

import com.hippo.fresh.utils.ResponseUtils;

public interface ForumService {

    //发帖
    public ResponseUtils save(Long userId,String title,String content);

    public ResponseUtils GetById(Long id);
}
