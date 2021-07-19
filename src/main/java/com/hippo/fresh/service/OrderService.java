package com.hippo.fresh.service;

import com.hippo.fresh.entity.Order;
import com.hippo.fresh.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {

    /** 判断订单id查找用户id */
    public Optional<Order> findById(Long id);

    /** 判断订单id查找用户id */
    public Order saveByUserId(Long userId);

    /** 创建订单 */
    public boolean CreateOrder(Long userId,List<Map<String,Object>> products);

    /**根据用户id查找该用户所有订单ids*/
    public List<Long> findIdsByUserId(Long userId);

    /**删除订单*/
    public boolean deleteOrder(Long userId,Long orderId);

}
