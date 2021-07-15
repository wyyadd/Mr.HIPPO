package com.hippo.fresh.controller;

import com.hippo.fresh.dao.ARepository;
import com.hippo.fresh.dao.OrderitemRepository;
import com.hippo.fresh.dao.OrderRepository;
import com.hippo.fresh.dao.ReceiverRepository;
import com.hippo.fresh.entity.*;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.OrderitemService;
import com.hippo.fresh.service.OrderService;
import com.hippo.fresh.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    private OrderRepository orderRepository;

    @Autowired
    private ReceiverRepository receiverRepository;

    @Autowired
    private OrderitemRepository orderItemRepository;

    @Autowired
    private ARepository aRepository;

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

    //测试接口
    @GetMapping("/c")
    public Map<String,Object> test3() {
        Map<String, Object> res = new HashMap<>();
        Optional<Orderitem> orderItem = orderItemRepository.findById(1L);
        return res;
    }

    //测试接口
    @GetMapping("/d")
    public Map<String,Object> test4() {
        Map<String, Object> res = new HashMap<>();
        Optional<Order> a = aRepository.findById(1L);
        res.put("1",a.get());
        return res;
    }

    //测试接口
    @GetMapping("/confirmation")
    public Map<String,Object> test1(@RequestBody HashMap<String,Object> map, HttpServletRequest request) {
        Map<String, Object> res = new HashMap<>();

        //从token中解析出用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        //解析出订单id
        Long orderId = Long.valueOf(map.get("orderId").toString());

        System.out.println(userId);
        System.out.println(orderId);

        Optional<Order> order = orderService.findById(orderId);

        //订单不存在
        if(!order.isPresent()){
            res.put("code",404);
            res.put("msg","支付信息获取失败");
        }
        else{
            //订单中用户id与token中解析出用户id不一致
            if(order.get().getUserId() != userId) {
                res.put("code",404);
                res.put("msg","支付信息获取失败");
            }
            //订单中用户id与token中解析出用户id一致
            else{
                Map<String, Object> data = new HashMap<>();
                data.put("orderItem",orderItemService.findSomeInformationByOrderId(orderId));
                data.put("receiver",receiverService.findSomeInformationByUserId(userId));

                res.put("code",200);
                res.put("msg","获取成功");
                res.put("data",data);
            }
        }
        return res;
    }



}
