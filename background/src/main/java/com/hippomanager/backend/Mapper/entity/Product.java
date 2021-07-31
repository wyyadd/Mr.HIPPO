package com.hippomanager.backend.Mapper.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.security.PrivateKey;
import java.util.Date;

@Data
public class Product {
    private long id;
    private String categoryFirst;
    private String categorySecond;
    private String name;
    private String picture;
    private String pictureUrl;
    private long price;
    private long stock;
    private String detail;
    private String status;
    private long salesAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
