package com.hippo.fresh.service.impl;


import com.hippo.fresh.dao.OrderRepository;
import com.hippo.fresh.dao.OrderitemRepository;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.dao.UserRepository;
import com.hippo.fresh.entity.Order;
import com.hippo.fresh.entity.Orderitem;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderitemRepository orderitemRepository;

    @Autowired
    private UserRepository userRepository;

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

            //根据用户id、订单id、商品id、商品数量添加订单条目
            orderitemRepository.save(new Orderitem(newOrder.getUserId(), newOrder.getId(),productId,p.getName(),p.getPictureUrl(),
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


    /**删除订单*/
    public boolean deleteOrder(Long userId,Long orderId){
        Optional<User> user = userRepository.findById(userId);
        //用户不存在
        if(!user.isPresent()){
            return false;
        }
        //用户存在
        else{
            Optional<Order> order = orderRepository.findById(orderId);
            //订单不存在
            if(!order.isPresent()){
                return false;
            }
            else{
                //订单中用户id 与 token中解析出不一致
                if(!order.get().getUserId().equals(userId)){
                    return false;
                }
                //成功删除
                else{
                    Order newOrder = order.get();
                    newOrder.setStatus(0);
                    orderRepository.save(newOrder);
                    return true;
                }
            }

        }
    }


    /**根据订单id查找该用户所有订单*/
    public List<Order> findAllByUserId(Long userId){
        return orderRepository.findAllByUserId(userId);
    }

    /**根据用户id查找用户订单，并按照创建时间倒叙排序*/
    public List<Order> findAllByUserIdOrderByCreateTimeDesc(Long userId){
        return  orderRepository.findAllByUserIdOrderByCreateTimeDesc(userId);
    }


    /**根据订单创建时间段返回订单信息*/
    public Map<String,Object> findByTime(Long userId){
        Map<String,Object> map = new HashMap<>();

        //times表示各个小时段的订单数目
        int [] times = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        List<Order> orders = orderRepository.findAllByUserId(userId);
        for(Order order:orders){
            for(int i =0;i < 24;i++){
                String timeFormat = String.format("%02d",i);
                String hour = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreateTime()).substring(11,13);
                if(hour.equals(timeFormat))
                    times[i]++;
            }
        }

        for(int i =0; i <24;i++){
            String timeFormat = String.format("%02d",i);
            map.put(timeFormat,times[i]);
        }
        return map;
    }

}
