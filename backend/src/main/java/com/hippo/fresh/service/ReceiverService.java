package com.hippo.fresh.service;

import com.hippo.fresh.utils.ResponseUtils;

import java.util.List;
import java.util.Map;

public interface ReceiverService {

    /**根据用户id查找该用户所有收件人部分信息：id、name、address、phone */
    public List<Map<String,Object>> findSomeInformationByUserId(Long id);

    /**根据收件人信息增加收件人*/
    public ResponseUtils save(Long userId,String name,String phone,String province,String city,
                              String district,String address);

    /**根据收件人id删除收件人*/
    public ResponseUtils delete(Long userId,Long receiverId);
}
