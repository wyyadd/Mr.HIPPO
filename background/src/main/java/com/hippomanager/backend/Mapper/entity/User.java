package com.hippomanager.backend.Mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private long status;
    private char identity;
    private Date createTime;
}
