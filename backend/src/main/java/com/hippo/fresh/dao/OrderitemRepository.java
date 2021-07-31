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

    //根据商品一级目录名称查找该用户一级目录商品个数
    @Query(value = "select count(category_first) from orderitem x where x.user_id =?1 and x.category_first =?2",nativeQuery = true)
    int countCategoryFirst(Long userId,String categoryFirst);


    //根据商品二级目录名称查找该用户一级目录商品个数
    @Query(value = "select count(category_second) from orderitem x where x.user_id =?1 and x.category_second =?2",nativeQuery = true)
    int countCategorySecond(Long userId,String categorySecond);


}