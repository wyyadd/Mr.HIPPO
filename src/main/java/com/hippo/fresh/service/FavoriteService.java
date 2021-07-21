package com.hippo.fresh.service;

import com.hippo.fresh.entity.Favorite;
import com.hippo.fresh.utils.ResponseUtils;

public interface FavoriteService {

    /**增加商品到收藏夹*/
    public ResponseUtils save(Long userId, Long productId);

    /**从收藏夹中删除商品*/
    public ResponseUtils delete(Long userId, Long productId);

    /**查找收藏夹商品*/
    public ResponseUtils find(Long userId);

}
