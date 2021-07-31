package com.hippomanager.backend.Mapper.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hippomanager.backend.Mapper.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user where username LIKE '%${username}%' " )
    List<User> selectUserByName(String username);
}

