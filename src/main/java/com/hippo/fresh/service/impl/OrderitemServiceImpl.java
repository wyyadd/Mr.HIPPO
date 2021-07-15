package com.hippo.fresh.service.impl;

import com.hippo.fresh.dao.OrderitemRepository;
import com.hippo.fresh.service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderitemServiceImpl implements OrderitemService {

    @Autowired
    private OrderitemRepository orderItemRepository;

    //根据用户id查找该订单所有商品条目部分信息：id、product_name、current_price、quantity、product_picture_url
    public List<Map<String,Object>> findSomeInformationByOrderId(Long id){
        return orderItemRepository.findSomeInformationByOrderId(id);
    }
}
