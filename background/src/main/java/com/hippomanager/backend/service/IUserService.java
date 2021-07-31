package com.hippomanager.backend.service;


import com.hippomanager.backend.Mapper.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserService {
    List<User> selectUserAll();
    int deleteUserById(long id);
    List<User> selectByName(String username);
    User getUserById(long id);
    int changeUserStatus(User user);
}
