package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.OrderitemRepository;
import com.hippo.fresh.dao.OrderRepository;
import com.hippo.fresh.dao.ReceiverRepository;
import com.hippo.fresh.entity.*;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.OrderitemService;
import com.hippo.fresh.service.OrderService;
import com.hippo.fresh.service.ProductService;
import com.hippo.fresh.service.ReceiverService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderitemService orderItemService;

    @Autowired
    private ReceiverService receiverService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReceiverRepository receiverRepository;

    @Autowired
    private OrderitemRepository orderItemRepository;

    //订单创建接口
    @PostMapping("/generation")
    public ResponseUtils generation(@RequestBody Map<String,Object> map, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        //解析products
        List<Map<String,Object>> products = (List<Map<String,Object>>)map.get("products");

        if(orderService.CreateOrder(userId,products))
            return ResponseUtils.response(200, "订单创建成功", jsonObject);
        else
            return ResponseUtils.response(404, "订单创建失败", jsonObject);
    }


    //订单信息获取接口
    @PostMapping("/information")
    public ResponseUtils information(@RequestBody HashMap<String,Object> map,HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();

        //从token中解析出用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();
        //解析出订单id
        Long orderId = Long.valueOf(map.get("orderId").toString());

        Optional<Order> order = orderService.findById(orderId);

        //订单不存在
        if(!order.isPresent()){
            return ResponseUtils.response(404, "订单信息获取失败", jsonObject);
        }
        else{
            //订单中用户id与token中解析出用户id不一致
            if(order.get().getUserId() != userId) {
                return ResponseUtils.response(404, "订单信息获取失败", jsonObject);
            }
            //订单中用户id与token中解析出用户id一致
            else{
                jsonObject.put("orderItem",orderItemService.findSomeInformationByOrderId(orderId));
                jsonObject.put("receiver",receiverService.findSomeInformationByUserId(userId));
                jsonObject.put("orderStatus",order.get().getStatus());
                jsonObject.put("orderPaymentMoney",order.get().getPaymentMoney());
                jsonObject.put("orderId",order.get().getId());
                return ResponseUtils.response(200, "订单信息获取成功",jsonObject);
            }
        }
    }

    //订单号列表获取接口
    @PostMapping("/ids")
    public ResponseUtils ids(HttpServletRequest request) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderIds",orderService.findIdsByUserId(userId));

        return ResponseUtils.response(200, "订单号列表获取成功", jsonObject);
    }


    //订单删除接口
    @PostMapping("/delete")
    public ResponseUtils deleteOrder(HttpServletRequest request,@RequestBody String jsStr) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        //获取订单id
        JSONObject jsonObject = JSON.parseObject(jsStr);
        Long orederId = jsonObject.getLong("orderId");

        if(orderService.deleteOrder(userId,orederId)){
            return ResponseUtils.response(200, "订单删除成功", jsonObject);
        }
        else{
            return ResponseUtils.response(404, "订单删除失败", jsonObject);
        }

    }


    //获取全部订单信息接口
    @PostMapping("/all/information")
    public ResponseUtils allInformation(HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        List<Map<String,Object>> res1 = new ArrayList<>();
        List<Order> orders = orderService.findAllByUserIdOrderByCreateTimeDesc(userId);
        for(Order order:orders) {
            Map<String,Object> res2 = new HashMap<>();
            res2.put("orderItem",orderItemService.findSomeInformationByOrderId(order.getId()));
            res2.put("orderStatus",order.getStatus());
            res2.put("orderPaymentMoney",order.getPaymentMoney());
            res2.put("orderId",order.getId());
            if(order.getStatus() != 0)
                res1.add(res2);
        }
        jsonObject.put("orders",res1);
        return ResponseUtils.response(200, "订单信息获取成功",jsonObject);
            }

    //不同订单状态返回用户订单总金额
    @PostMapping("/money")
    public ResponseUtils money(HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        if(orderRepository.findAllByUserIdAndAndStatus(userId,1).size() == 0 )
            jsonObject.put("待支付",0);
        else
            jsonObject.put("待支付",orderRepository.status(userId,1));

        if(orderRepository.findAllByUserIdAndAndStatus(userId,2).size() == 0 )
            jsonObject.put("已支付",0);
        else
            jsonObject.put("已支付",orderRepository.status(userId,2));

        return ResponseUtils.response(200, "订单总金额信息获取成功",jsonObject);
    }

    //根据订单创建时间段返回订单信息
    @PostMapping("/time")
    public ResponseUtils time(HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        jsonObject.put("time",orderService.findByTime(userId));
        return ResponseUtils.response(200, "订单时间段获取成功",jsonObject);
    }

}
