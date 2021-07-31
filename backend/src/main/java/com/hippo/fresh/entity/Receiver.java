package com.hippo.fresh.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "receiver") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Receiver {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;//收货人id

    @Column
    private Long userId;//用户id

    @Column
    private String name ;//收件人姓名

    @Column
    private String phone;//收件人电话

    @Column
    private String province;//收件人省份

    @Column
    private String city;//收件人城市

    @Column
    private String district;//收件人县/区

    @Column
    private String address;//收件人详细地址

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;//收件人信息创建时间

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;//收件人信息更新时间

    public Receiver(Long userId, String name, String phone, String province, String city, String district, String address, Timestamp createTime, Timestamp updateTime) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

}
