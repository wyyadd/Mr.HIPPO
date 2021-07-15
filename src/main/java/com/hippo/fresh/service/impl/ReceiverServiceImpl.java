package com.hippo.fresh.service.impl;

import com.hippo.fresh.dao.ReceiverRepository;
import com.hippo.fresh.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReceiverServiceImpl  implements ReceiverService {

    @Autowired
    private ReceiverRepository receiverRepository;


    /**根据用户id查找该用户所有收件人部分信息：id、name、address、phone */
    public List<Map<String,Object>> findSomeInformationByUserId(Long id){
        return receiverRepository.findSomeInformationByUserId(id);
    }
}
