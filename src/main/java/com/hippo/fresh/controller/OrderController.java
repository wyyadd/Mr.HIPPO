package com.hippo.fresh.controller;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    //测试接口
    @GetMapping("/a")
    public Map<String,Object> test1() {
        Map<String, Object> res = new HashMap<>();
        Optional<Order> order = orderRepository.findById(1L);
        return res;
    }

    //测试接口
    @GetMapping("/b")
    public Map<String,Object> test2() {
        Map<String, Object> res = new HashMap<>();
        Optional<Receiver> order = receiverRepository.findById(1L);
        res.put("1",order.get());
        return res;
    }

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
    @GetMapping("/information")
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
                return ResponseUtils.response(200, "订单信息获取成功",jsonObject);
            }
        }
    }

    //订单号列表获取接口
    @GetMapping("/ids")
    public ResponseUtils ids(HttpServletRequest request) {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderIds",orderService.findIdsByUserId(userId));

        return ResponseUtils.response(200, "订单号列表获取成功", jsonObject);
    }

}
