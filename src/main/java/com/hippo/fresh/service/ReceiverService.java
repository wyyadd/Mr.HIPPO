package com.hippo.fresh.service;

import java.util.List;
import java.util.Map;

public interface ReceiverService {

    /**根据用户id查找该用户所有收件人部分信息：id、name、address、phone */
    public List<Map<String,Object>> findSomeInformationByUserId(Long id);
}
