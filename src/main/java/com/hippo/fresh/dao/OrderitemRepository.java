package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderitemRepository extends JpaRepository<Orderitem, Long> {

    //根据用户id查找该订单所有商品条目部分信息：id、product_name、current_price、quantity、product_picture_url
    @Query(value = "select id,product_name,current_price,quantity,product_picture_url from orderitem x where x.order_id =?1 ",nativeQuery = true)
    List<Map<String,Object>> findSomeInformationByOrderId(Long id);


    @Override
    Optional<Orderitem> findById(Long aLong);

    List<Orderitem> findByOrderId(Long orderId);

}