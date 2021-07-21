package com.hippo.fresh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "favorite") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Favorite {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;//收藏夹id

    @Column
    private Long userId;//用户id

    public Favorite(Long userId, Long productId, String productName, String productPicture, Double productPrice, Integer status, Timestamp createTime) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.productPicture = productPicture;
        this.productPrice = productPrice;
        this.status = status;
        this.createTime = createTime;
    }

    @Column
    private Long productId;//商品id

    @Column
    private String productName;//商品名称

    @Column
    private String productPicture;//商品图片

    @Column
    private Double productPrice;//商品价格

    @Column
    private Integer status;//商品状态


    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;//商品创建时间

}