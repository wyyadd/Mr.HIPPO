package com.hippo.fresh.service;

import com.hippo.fresh.entity.Orderitem;

import java.util.List;
import java.util.Map;

public interface OrderitemService {

    //根据用户id查找该订单所有商品条目部分信息：id、product_name、current_price、quantity、product_picture_url
    public List<Map<String,Object>> findSomeInformationByOrderId(Long id);

//    //根据订单id、商品id、商品数量添加订单条目
//    public Orderitem saveByProductId(Long orderId,Long productId,Long quantity);


}
