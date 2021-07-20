package com.hippo.fresh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.VerificationRepository;
import com.hippo.fresh.entity.Verification;
import com.hippo.fresh.service.MailService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private VerificationRepository verificationRepository;

    public ResponseUtils sendVerCode(String receiver, String verCode){

        SimpleMailMessage simpMsg = new SimpleMailMessage();

        simpMsg.setFrom("459155106@qq.com");//发件人邮箱
        simpMsg.setTo(receiver);//收件人邮箱
        simpMsg.setSubject("河马生鲜购物平台验证码");//主题
        simpMsg.setText("尊敬的用户,您好:\n"
                + "\n您本次请求注册河马生鲜购物平台的邮件验证码为: " + verCode + " ,本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");//内容

        javaMailSender.send(simpMsg);//发送邮件

        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        Verification verification = verificationRepository.save(new Verification(verCode,createTime));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("verCodeId",verification.getId());
        return ResponseUtils.response(200, "验证码发送成功", jsonObject);

    }
}
