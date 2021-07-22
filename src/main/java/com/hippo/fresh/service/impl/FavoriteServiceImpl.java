package com.hippo.fresh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.FavoriteRepository;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Favorite;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.service.FavoriteService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;


@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductRepository productRepository;

    /**增加商品到收藏夹*/
    public ResponseUtils save(Long userId, Long productId){

        JSONObject jsonObject = new JSONObject();
        Optional<Product> p = productRepository.findById(productId);
        //如果商品不存在
        if(!p.isPresent()){
            return ResponseUtils.response(404, "收藏夹添加商品不存在", jsonObject);
        }
        else{
            //该用户已收藏该商品
            if(favoriteRepository.findByUserIdAndProductId(userId,productId).isPresent()){
                return ResponseUtils.response(405, "你已收藏该商品", jsonObject);
            }
            else {
                Product product = p.get();
                Timestamp creatTime = new Timestamp(System.currentTimeMillis());
                favoriteRepository.save(new Favorite(userId, productId, product.getName(),
                        product.getPictureUrl(), product.getPrice(), product.getStatus(), creatTime));
                return ResponseUtils.response(200, "收藏夹添加商品成功", jsonObject);
            }
        }
    }


    /**从收藏夹中删除商品*/
    public ResponseUtils delete(Long userId, Long productId){

        JSONObject jsonObject = new JSONObject();

        Optional<Favorite> favorite = favoriteRepository.findByUserIdAndProductId(userId,productId);

        //该用户收藏夹中不存在该商品
        if(!favorite.isPresent()){
            return ResponseUtils.response(404, "收藏夹删除商品失败", jsonObject);
        }
        else{
            favoriteRepository.deleteById(favorite.get().getId());
            return ResponseUtils.response(200, "收藏夹删除商品成功", jsonObject);
        }

    }


    /**查找收藏夹商品*/
    public ResponseUtils find(Long userId){
        JSONObject jsonObject = new JSONObject();

        List<Favorite> favorites = favoriteRepository.findAllByUserIdOrderByCreateTimeDesc(userId);
        List<Map<String,Object>> res = new ArrayList<>();

        for(Favorite favorite:favorites){
            Map<String,Object> map= new HashMap<>();
            map.put("productId",favorite.getProductId());
            map.put("productName",favorite.getProductName());
            map.put("productPicture",favorite.getProductPicture());
            map.put("oldPrice",favorite.getProductPrice());
            map.put("productStatus",favorite.getStatus());
            map.put("currentPrice",favorite.getCurrentPrice());
            res.add(map);
        }
        jsonObject.put("products",res);
        return ResponseUtils.response(200, "收藏夹查找成功", jsonObject);

    }
}
