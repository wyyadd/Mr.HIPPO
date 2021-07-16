package com.hippo.fresh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.ReceiverRepository;
import com.hippo.fresh.dao.UserRepository;
import com.hippo.fresh.entity.Receiver;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.exception.UserHasExistException;
import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReceiverRepository receiverRepository;

    private JSONObject jsonObject;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user){
    return userRepository.save(user);
    }


    /** 增加 */
    public Optional<User> findById(Long aLong){
        return userRepository.findById(aLong);
    }

    /** 根据用户名查找用户 */
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    /** 判断用户是否存在 */
    public boolean exitsUser(String username)
    {
        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent();
    }

    /** 用户注册
     * @return json
     * */
    public ResponseUtils register(String username, String password, String email) {

        Optional<User> user = userRepository.findByUsername(username);

        boolean existsUser = user.isPresent();
        //如果用户名不存在，成功创建用户
        if (!existsUser) {
//            System.out.println("true");
            User newUser = userRepository.save(new User(username,bCryptPasswordEncoder.encode(password),email));
            jsonObject = new JSONObject();
            jsonObject.put("id", newUser.getId());
            jsonObject.put("username", newUser.getUsername());
            jsonObject.put("email", newUser.getEmail());
            return ResponseUtils.response(200,"注册成功", jsonObject);
        } else {
            jsonObject = new JSONObject();
            jsonObject.put("username",username);
            jsonObject.put("email",email);
            throw new UserHasExistException(jsonObject);
        }
    }

    /** 用户主页 */
    public ResponseUtils information(Long userId){
        jsonObject = new JSONObject();

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            List<Receiver> allReceiver = receiverRepository.findAllByUserId(userId);

            jsonObject.put("id", user.get().getId());
            jsonObject.put("username", user.get().getUsername());
            jsonObject.put("email", user.get().getEmail());
            jsonObject.put("avatar", user.get().getAvatar());
            jsonObject.put("receiver", allReceiver);
            return ResponseUtils.success("用户信息返回成功",jsonObject);
        }
        else
        {
            jsonObject.put("id",userId);
            throw new UserNotExistException(jsonObject);
        }
    }

}
