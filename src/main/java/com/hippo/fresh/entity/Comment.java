package com.hippo.fresh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Comment {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;//评论id

    public Comment(String comment, Long userId, String username, String userAvatar, Long productId, String productName, String productUrl, Timestamp createTime) {
        this.comment = comment;
        this.userId = userId;
        this.username = username;
        this.userAvatar = userAvatar;
        this.productId = productId;
        this.productName = productName;
        this.productUrl = productUrl;
        this.createTime = createTime;
    }

    @Column
    private String comment;//评论

    @Column
    private Long userId;//用户id

    @Column
    private String username;//用户名

    @Column
    private String userAvatar;//用户头像

    @Column
    private Long productId;//商品id

    @Column
    private String productName;//商品名

    @Column
    private String productUrl;//商品图片

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;//商品更新时间
}
