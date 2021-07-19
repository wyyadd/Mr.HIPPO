package com.hippo.fresh.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.service.MailService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Random;

@RestController
@RequestMapping("/api/verification")
public class VerificationController {

    @Autowired
    private MailService mailService;

    private static final String SYMBOLS = "0123456789";
    /**
     * Math.random生成的是一般随机数，采用的是类似于统计学的随机数生成规则，其输出结果很容易预测，因此可能导致被攻击者击中。
     * 而SecureRandom是真随机数，采用的是类似于密码学的随机数生成规则，其输出结果较难预测，若想要预防被攻击者攻击，最好做到使攻击者根本无法，或不可能鉴别生成的随机值和真正的随机值。
     */
    private static final Random RANDOM = new SecureRandom();

    public static String generateVerCode() {
        char[] nonceChars = new char[6];
        for (int i = 0; i < nonceChars.length; i++) {
            nonceChars[i] = SYMBOLS.charAt(RANDOM.nextInt(nonceChars.length));
        }
        return new String(nonceChars);
    }

    //发送邮箱验证码接口
    @PostMapping("/get")
    public ResponseUtils get(@RequestBody JSONObject jsonObject){

       String receiver = jsonObject.getString("email");

       //产生邮箱验证码
        String verCode = generateVerCode();
        //发送验证码
        return mailService.sendVerCode(receiver,verCode);
    }

}
