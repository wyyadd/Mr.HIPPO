package com.hippomanager.backend.Mapper.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.security.PrivateKey;
import java.util.Date;

@Data
public class Ord {
    private long id;
    private long userId;
    private long receiverId;
    private String receiverName;
    private String receiverAddress;
    private long itemCount;
    private long status;
    private long paymentMoney;
    private String paymentType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sendTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date completeTime;
}
