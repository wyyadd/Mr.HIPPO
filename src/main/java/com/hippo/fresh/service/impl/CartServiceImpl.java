package com.hippo.fresh.service.impl;

import com.hippo.fresh.dao.CartRepository;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Cart;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class CartServiceImpl  implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    /**根据用户id查找购物车*/
    public List<Map<String,Object>> findCartInformationByUserId(Long userId){
        List<Map<String,Object>> res = new ArrayList<>();
        List<Cart> carts = cartRepository.findByUserIdOrderByUpdateTimeDesc(userId);
        for(Cart cart:carts){
            Map<String,Object> map = new HashMap<>();
            map.put("productId",cart.getProductId());
            map.put("productName",cart.getProductName());
            map.put("productPicture",cart.getProductPicture());
            map.put("OldPrice",cart.getProductPrice());
            map.put("currentPrice",cart.getCurrentPrice());
            map.put("productQuantity",cart.getQuantity());
            map.put("productStatus",cart.getStatus());
            res.add(map);
        }
        return res;
    }


    /**根据用户id、商品id、商品数量更新购物车 */
    public boolean updateOne(Long userId,Long productId,Long quantity){

//        Optional<Cart> cart = cartRepository.findByUserIdAndProductId(userId,productId);
//        //该商品在原购物车中存在
//        if(cart.isPresent()) {
//            //更改商品数量即可
//            cart.get().setQuantity(quantity);
//            cartRepository.save( cart.get());
//        }
//        else{
//            Product product = productRepository.findById(productId).get();
//            cartRepository.save(new Cart(userId, productId, product.getName(), product.getPicture(), product.getPrice(),quantity));
//        }
        Timestamp creatTime = new Timestamp(System.currentTimeMillis());
        Timestamp updataTime= new Timestamp(System.currentTimeMillis());

        Product product = productRepository.findById(productId).get();
        cartRepository.save(new Cart(userId, productId, product.getName(), product.getPicture(),
                product.getPrice(),quantity,product.getStatus(),creatTime,updataTime));

        return true;
    }
}
