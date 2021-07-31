package com.hippo.fresh.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ord") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;//收货人id

    @Column
    private Long userId ;//用户Id

    @Column
    private Long receiverId ;//收件人Id

    @Column
    private String receiverName;//收件人姓名

    @Column
    private String receiverAddress;//收件人地址

    @Column
    private Long itemCount;//订单商品数量

    @Column
    private Integer status ;//订单状态：0-已取消，1-未付款，2-已付款，3-已发货

    @Column
    private Double paymentMoney;//实际支付金额

    @Column
    private String paymentType;//支付类型

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp paymentTime;//支付时间

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp sendTime;//发货时间

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;//创建时间

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp completeTime;//完成时间

    public Order(Long userId) {
        this.userId = userId;
    }

}
