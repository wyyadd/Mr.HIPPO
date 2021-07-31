package com.hippomanager.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hippomanager.backend.Mapper.Dao.UserMapper;
import com.hippomanager.backend.Mapper.entity.User;
import com.hippomanager.backend.service.IUserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectUserAll() {
        Wrapper<User> wrapper = new QueryWrapper();
        return userMapper.selectList(wrapper);
    }

    @Override
    public int deleteUserById(long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public List<User> selectByName(String username) {
        return userMapper.selectUserByName(username);
    }

    @Override
    public User getUserById(long id) {
        User user=userMapper.selectById(id);
        return user;
    }

    @Override
    public int changeUserStatus(User user) {
        return userMapper.updateById(user);
    }

}