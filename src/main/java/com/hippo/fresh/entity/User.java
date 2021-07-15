package com.hippo.fresh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User<name> {

    @Id// 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;//用户id

    @Column
    private String username;// 用户名

    @Column
    private String password;// 密码

    @Column
    private String email;// 用户邮箱

    @Column
    private String phone;// 用户电话

    @Column
    private String avatar;// 用户头像


    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;//用户创建时间

    //用户名、用户密码、用户邮箱构造函数
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
