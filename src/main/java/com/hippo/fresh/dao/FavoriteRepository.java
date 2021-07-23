package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Cart;
import com.hippo.fresh.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    //根据商品id查找收藏夹
    Optional<Favorite> findByUserIdAndProductId(Long userId,Long productId);

    //根据用户id查找该用户收藏夹所有商品
    List<Favorite> findAllByUserIdOrderByCreateTimeDesc(Long userId);

}
