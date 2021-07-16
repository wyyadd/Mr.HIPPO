package com.hippo.fresh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "orderitem") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orderitem {
    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;//订单商品id

    @Column
    private Long orderId;//订单id

    @Column
    private Long productId;//商品id

    @Column
    private String productName;//商品名称

    @Column
    private String productPicture;//商品图片

    @Column
    private String productPictureUrl;//商品图片url

    @Column
    private Double currentPrice;//商品加入订单时价格


    @Column
    private Long quantity;//订单中该商品数量

    @Column
    private Double totalPrice;//商品总价格

    @Column
    private String productDetails;//商品详情

    public Orderitem(Long orderId, Long productId, String productName, String productPictureUrl,
                     Double currentPrice, Long quantity, Double totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productPictureUrl = productPictureUrl;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

}