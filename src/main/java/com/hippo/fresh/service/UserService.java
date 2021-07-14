package com.hippo.fresh.service;

import com.hippo.fresh.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    /** 增加用户 */
    public User save(User user);

    /** 查找全部 */
    public List<User> findAll();

    /** 根据id查找 */
    public Optional<User> findById(Long aLong);

    /** 根据用户名查找用户 */
    public Optional<User> findByUsername(String username);

    /** 判断用户是否存在 */
    public boolean exitsUser(String username);

    /** 用户注册 */
    public Map<String,Object> register(String username, String password,String email);

    /** 用户登录 */
//    public Map<String,Object> login(String username, String password);

    /** 用户主页 */
    public Map<String,Object> information(Long userId);


}
