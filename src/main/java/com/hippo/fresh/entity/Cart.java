package com.hippo.fresh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "cart") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Cart {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;//购物车id

    @Column
    private Long userId;//用户id

    @Column
    private Long productId;//商品id

    @Column
    private String productName;//商品名称

    @Column
    private String productPicture;//商品图片

    @Column
    private double productPrice;//商品价格

    @Column
    private Long quantity;//商品数量

    @Column
    private Integer status;//商品状态

    @Column
    private Timestamp createTime;//商品创建时间

    @Column
    private Timestamp updateTime;//商品更新时间
}
