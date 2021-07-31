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

    /**收藏夹商品降价10%提醒*/
    public boolean priceDrop10Reminder(Favorite favorite);

    /**收藏夹商品降价20%提醒*/
    public boolean priceDrop20Reminder(Favorite favorite);

    /**收藏夹商品降价30%提醒*/
    public boolean priceDrop30Reminder(Favorite favorite);

    /**收藏夹商品最低价格提醒*/
    public boolean priceLowestReminder(Favorite favorite);

}
