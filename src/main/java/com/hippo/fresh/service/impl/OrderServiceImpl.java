package com.hippo.fresh.service.impl;


import com.hippo.fresh.dao.OrderRepository;
import com.hippo.fresh.entity.Order;
import com.hippo.fresh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    /** 判断订单id查找用户id */
    /** 判断订单id查找用户id */
    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }

}
