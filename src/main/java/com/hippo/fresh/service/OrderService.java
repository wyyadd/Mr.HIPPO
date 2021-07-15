package com.hippo.fresh.service;

import com.hippo.fresh.entity.Order;
import com.hippo.fresh.entity.User;

import java.util.Optional;

public interface OrderService {

    /** 判断订单id查找用户id */
    public Optional<Order> findById(Long id);
}
