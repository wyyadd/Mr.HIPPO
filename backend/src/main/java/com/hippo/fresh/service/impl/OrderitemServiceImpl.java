package com.hippo.fresh.service.impl;

import com.hippo.fresh.dao.OrderitemRepository;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Order;
import com.hippo.fresh.entity.Orderitem;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class OrderitemServiceImpl implements OrderitemService {

    @Autowired
    private OrderitemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    /**根据用户id查找该订单所有商品条目部分信息：id、product_name、current_price、quantity、product_picture_url*/
    public List<Map<String,Object>> findSomeInformationByOrderId(Long id){
//        return orderItemRepository.findSomeInformationByOrderId(id);

        List<Map<String,Object>> res = new ArrayList<>();

        List<Orderitem> orderitems = orderItemRepository.findByOrderId(id);

        for(Orderitem orderitem:orderitems){
            Map<String, Object> x = new HashMap<>();
            x.put("name",orderitem.getProductName());
            x.put("price",orderitem.getCurrentPrice());
            x.put("quantity",orderitem.getQuantity());
            x.put("pictureUrl",orderitem.getProductPictureUrl());
            x.put("details",orderitem.getProductDetails());
            res.add(x);
        }
        return res;

    }

//    /** 根据订单id、商品id、商品数量添加订单条目*/
//    public Orderitem saveByProductId(Long orderId, Long productId,Long quantity){
//
//        Product product = productRepository.findById(productId).get();
//        return orderItemRepository.save(new Orderitem(orderId,productId,product.getName(),product.getPictureUrl(),
//                product.getPrice(),quantity, product.getPrice()*quantity));
//    }


}
