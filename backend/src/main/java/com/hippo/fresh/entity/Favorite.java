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

    @Column
    private Long productId;//商品id

    @Column
    private String productName;//商品名称

    @Column
    private String productPicture;//商品图片

    @Column
    private Double oldPrice;//商品加入购物车时价格

    @Column
    private Double currentPrice;//商品的当前价格

    @Column
    private Double lowestPrice;//商品加入购物车以后的最低价格

    @Column
    private Integer status;//商品状态

    @Column
    private Integer notifyTen;//商品降价10%通知

    @Column
    private Integer notifyTwenty;//商品降价20%通知

    @Column
    private Integer notifyThirty;//商品降价30%通知

    @Column
    private Integer notifyLowest;//商品最低价格通知


    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;//商品创建时间

    public Favorite(Long userId, Long productId, String productName, String productPicture, Double oldPrice, Double currentPrice, Double lowestPrice, Integer status, Integer notifyTen, Integer notifyTwenty, Integer notifyThirty, Integer notifyLowest, Timestamp createTime) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.productPicture = productPicture;
        this.oldPrice = oldPrice;
        this.currentPrice = currentPrice;
        this.lowestPrice = lowestPrice;
        this.status = status;
        this.notifyTen = notifyTen;
        this.notifyTwenty = notifyTwenty;
        this.notifyThirty = notifyThirty;
        this.notifyLowest = notifyLowest;
        this.createTime = createTime;
    }
}