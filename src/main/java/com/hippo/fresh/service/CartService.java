package com.hippo.fresh.service;

import com.hippo.fresh.entity.Cart;
import com.hippo.fresh.entity.Favorite;

import java.util.List;
import java.util.Map;

public interface CartService {

    public List<Map<String,Object>> findCartInformationByUserId(Long id);

    /**根据用户id、商品id、商品数量更新购物车 */
    public boolean updateOne(Long userId,Long productId,Long quantity);


    /**购物车商品降价10%提醒*/
    public boolean priceDrop10Reminder(Cart cart);

    /**购物车商品降价20%提醒*/
    public boolean priceDrop20Reminder(Cart cart);

    /**购物车商品降价30%提醒*/
    public boolean priceDrop30Reminder(Cart cart);

    /**购物车商品最低价格提醒*/
    public boolean priceLowestReminder(Cart cart);
}
