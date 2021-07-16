package com.hippo.fresh.service.impl;


import com.hippo.fresh.dao.OrderRepository;
import com.hippo.fresh.dao.OrderitemRepository;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Order;
import com.hippo.fresh.entity.Orderitem;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderitemRepository orderitemRepository;

    /** 判断订单id查找用户id */
    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }


    /** 根据用户id 生成新订单 */
    public Order saveByUserId(Long userId){
        return orderRepository.save(new Order(userId));
    }


    /** 创建订单 */
    public boolean CreateOrder(Long userId, List<Map<String,Object>> products){

        //生成新订单
        Order newOrder = orderRepository.save(new Order(userId));

        //设置订单商品条目数量
        newOrder.setItemCount(new Long((long)products.size()));

        Double paymentMoney = new Double(0);

        //将商品加入订单
        for(Map<String,Object> product:products){
            Long productId = Long.valueOf(product.get("id").toString());//解析出productId
            Long productQuantity = Long.valueOf(product.get("quantity").toString());//解析出productQuantity

            Product p = productRepository.findById(productId).get();
            paymentMoney = paymentMoney + p.getPrice() * productQuantity;

            //根据订单id、商品id、商品数量添加订单条目
            orderitemRepository.save(new Orderitem(newOrder.getId(),productId,p.getName(),p.getPictureUrl(),
                    p.getPrice(),productQuantity, p.getPrice()*productQuantity));
        }
        //设置订单总金额
        newOrder.setPaymentMoney(paymentMoney);
        //设置订单状态 1:未支付
        newOrder.setStatus(new Integer(1));
        //设置订单创建时间
        newOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));

        orderRepository.save(newOrder);
        return true;
    }

    /**根据用户id查找该用户所有订单*/
    public List<Long> findIdsByUserId(Long userId){
        List<Long> ids = new ArrayList<>();
        List<Order> orders= orderRepository.findAllByUserId(userId);
        for(Order order:orders){
            ids.add(order.getId());
        }
        return ids;
    }


}
