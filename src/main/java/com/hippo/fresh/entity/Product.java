package com.hippo.fresh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int id;//商品id

    @Column
    private Long categoryId;//商品目录id

    @Column
    private String name;//商品名称

    @Column
    private String picture;//商品图片

    @Column
    private String pictureUrl;//商品图片url

    @Column
    private double price;//商品单价

    @Column
    private Long stock;//商品库存

    @Column
    private String detail;//商品详细信息

    @Column
    private Integer status;//商品状态，1-在售 2-下架

    @Column
    private Long salesAmount;//商品销售量

    @Column
    private Timestamp createTime;//商品创建时间

    @Column
    private Timestamp updateTime;//商品更新时间
}
