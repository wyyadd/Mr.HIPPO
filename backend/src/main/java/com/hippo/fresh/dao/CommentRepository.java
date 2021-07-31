package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    //根据商品id查找评论
    public List<Comment> findByProductId(Long productId);

    //根据userName查找评论
    public List<Comment> findByUsername(String userName);

    //根据userId查找评论
    public List<Comment> findByUserId(Long userId);

}
