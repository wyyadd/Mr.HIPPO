package com.hippo.fresh.service;

import com.alipay.easysdk.kernel.util.JsonUtil;
import com.hippo.fresh.utils.ResponseUtils;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    /**查找用户消息通知*/
    public ResponseUtils find(Long userId);

    /**修改消息通知阅读状态*/
    public boolean modifyReadStatus(Long noticeId);

}
