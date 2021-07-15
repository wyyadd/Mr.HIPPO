package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CartRepository extends JpaRepository<Cart, Long> {

    //根据用户id查找该用户购物车所有商品的id、quantity
    @Query(value = "select id,quantity from cart x where x.user_id =?1 ",nativeQuery = true)
    List<Map<String,Object>> findCartInformationByUserId(Long id);

}
