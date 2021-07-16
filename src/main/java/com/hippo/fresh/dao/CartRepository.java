package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    //根据用户id查找该用户购物车所有商品的id、quantity
    @Query(value = "select id,quantity from cart x where x.user_id =?1 ",nativeQuery = true)
    List<Map<String,Object>> findCartInformationByUserId(Long id);


    //根据用户id和商品id查找购物车
    Optional<Cart> findByUserIdAndProductId(Long userId,Long productId);

    //根据用户id查找购物车
    List<Cart> findByUserId(Long userId);

    @Modifying
    void deleteByUserId(long userId);


    //    //根据用户id和商品id修改购物车商品数量
//    @Modifying
//    @Query(value ="update user x set x.quantity = ?3 where x.userId = ?1 and x.productId = ?1",nativeQuery = true)
//    int setProductQuantity(Long userId,Long productId,Long quantity);

//    //根据用户id和商品id修改购物车商品数量
//    @Modifying
//    @Query(value ="update cart x set x.quantity = 1 where x.product_id = ?1 ",nativeQuery = true)
//    int setProductQuantity(Long productId,Long quantity);
}
