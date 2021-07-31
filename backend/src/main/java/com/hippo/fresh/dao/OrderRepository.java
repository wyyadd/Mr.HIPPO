package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    //根据用户id查找用户订单，并按照创建时间倒叙排序
    List<Order> findAllByUserIdOrderByCreateTimeDesc(Long userId);

    //计算用户某一状态订单的总金额
    @Query(value = "select sum(payment_money) from ord x where x.user_id =?1 and x.status =?2",nativeQuery = true)
    double status(Long userId,Integer orderStatus);

    //根据用户id和订单状态查找订单
    List<Order> findAllByUserIdAndAndStatus(Long userId,Integer orderStatus);
}
