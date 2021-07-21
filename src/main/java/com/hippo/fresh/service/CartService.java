package com.hippo.fresh.service;

import java.util.List;
import java.util.Map;

public interface CartService {

    public List<Map<String,Object>> findCartInformationByUserId(Long id);

    /**根据用户id、商品id、商品数量更新购物车 */
    public boolean updateOne(Long userId,Long productId,Long quantity);
}
