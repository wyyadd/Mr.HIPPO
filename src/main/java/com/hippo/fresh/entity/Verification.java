package com.hippo.fresh.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "verification") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Verification{

    @Id// 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;//验证码id

    @Column
    private String verCode;// 验证码

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;//验证码创建时间

    public Verification(String verCode, Timestamp createTime) {
        this.verCode = verCode;
        this.createTime = createTime;
    }
}
