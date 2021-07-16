package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Override
    Optional<Order> findById(Long aLong);


    //增加订单
    @Override
    public Order save(Order order);

    //根据用户id查找该用户所有订单
    List<Order> findAllByUserId(Long userId);
}
