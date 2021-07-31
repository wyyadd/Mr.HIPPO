package com.hippo.fresh.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.factory.Factory;
import com.hippo.fresh.dao.OrderRepository;
import com.hippo.fresh.dao.ReceiverRepository;
import com.hippo.fresh.entity.Order;
import com.hippo.fresh.entity.Receiver;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/api/alipay")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReceiverRepository receiverRepository;

    @PostMapping("/topay")
    @ResponseBody
    public String toPay(@RequestBody JSONObject jsonObject,HttpServletRequest request) throws Exception {

        //从token中获取用户id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        Long orderId = jsonObject.getLong("orderId");
        Long receiverId = jsonObject.getLong("receiverId");

        Order order = orderRepository.findById(orderId).get();
        Receiver receiver = receiverRepository.findById(receiverId).get();
        order.setReceiverId(receiverId);
        order.setReceiverName(receiver.getName());
        order.setReceiverAddress(receiver.getAddress());
        order.setPaymentType(jsonObject.getString("PaymentType"));
        orderRepository.save(order);

        String paymentMoney = String.format("%.2f", order.getPaymentMoney()).toString();
        return alipayService.toPay(orderId.toString(),paymentMoney);
    }

    @PostMapping("/callback")
    @ResponseBody
    public String notifyCallback(HttpServletRequest request) throws Exception {
//        System.out.println("进入异步");
        String success = "success";
        String failure = "failure";

        // https://opendocs.alipay.com/open/54/00y8k9 新老版本说明中有异步通知的新版说明
        // 获取支付宝异步回调信息, 将其转为 Map<String, String>
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }

//        System.out.println(params);

        Order order = orderRepository.findById(Long.valueOf(params.get("out_trade_no"))).get();
        Timestamp  PaymentTime= new Timestamp(System.currentTimeMillis());
        order.setStatus(2);
        order.setPaymentTime(PaymentTime);
        orderRepository.save(order);

        // 新版 SDK 不用移除 sign_type
        // params.remove("sign_type");

        // 验签
        boolean signVerified = Factory.Payment.Common().verifyNotify(params);

        if(signVerified){ // 验签通过
//            System.out.println("通过验签");
            return success;
        }else{ // 验签失败
            return failure;
        }
    }

    @GetMapping("/query")
    @ResponseBody
    public Object queryTradeStatus(String outTradeNo) throws Exception {
        Object result = alipayService.queryTradeStatus(outTradeNo);
        return result;
    }
}
