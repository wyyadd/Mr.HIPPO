package com.hippo.fresh.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "forum") //@Table来指定和哪个数据表对应;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Forum {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;//通知id

    @Column
    private Long userId;//用户id

    @Column
    private String username;//用户名

    @Column
    private String userAvatar;//用户头像

    @Column
    private String title;//发帖标题

    @Column
    private String content;//发帖内容

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;//发帖时间

    public Forum(Long userId, String username, String userAvatar,String title,String content, Timestamp createTime) {
        this.userId = userId;
        this.username = username;
        this.userAvatar = userAvatar;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

}
