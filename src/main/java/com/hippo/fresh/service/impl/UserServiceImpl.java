package com.hippo.fresh.service.impl;

import com.hippo.fresh.dao.ReceiverRepository;
import com.hippo.fresh.dao.UserRepository;
import com.hippo.fresh.entity.Receiver;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReceiverRepository receiverRepository;


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

        if( user.isPresent())
            return true;
        else
            return false;
    }

    /** 用户注册 */
    public Map<String,Object> register(String username, String password,String email) {


        Map<String, Object> res = new HashMap<>();

        Optional<User> user = userRepository.findByUsername(username);

        boolean existsUser = user.isPresent();
        //如果用户名不存在，成功创建用户
        if (!existsUser) {
//            System.out.println("true");
            User newUser = userRepository.save(new User(username,bCryptPasswordEncoder.encode(password),email));
            Map<String, Object> data = new HashMap<>();
            data.put("id", newUser.getId());
            data.put("username", newUser.getUsername());
            data.put("email", newUser.getPassword());
            res.put("code", 200);
            res.put("msg", "注册成功");
            res.put("data", data);
        } else {
            res.put("code", 409);
            res.put("msg", "用户名已存在");
        }
        return res;
    }

    /** 用户登录 */
//    public Map<String,Object> login(String username, String password) {
//
//        Map<String, Object> res = new HashMap<>();
//
//        Optional<User> userForBase= userRepository.findByUsername(username);

//        //该用户名用户不存在
//        if(!userForBase.isPresent()){
//            res.put("code","401");
//            res.put("msg","用户名与密码不匹配");
//            return res;
//        }else {
//            User user = userForBase.get();
//            if (!user.getPassword().equals(password)){
//                //用户密码错误
//                res.put("code","401");
//                res.put("msg","用户名与密码不匹配");
//                return res;
//            }else {
//                //生成token
//                //String token = tokenService.getToken(userForBase);
//
//                Map<String, Object> date = new HashMap<>();
//                date.put("id", user.getId());
//                date.put("username", user.getUsername());
//                res.put("code","200");
//                res.put("msg","用户登录成功");
//                res.put("token", "xxx.yyy.zzz");
//                res.put("date",date);
//                return res;
//            }
//        }
//    }

    /** 用户主页 */
    public Map<String,Object> information(Long userId){
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            List<Receiver> allReceiver = receiverRepository.findAllByUserId(userId);

            data.put("id", user.get().getId());
            data.put("username", user.get().getUsername());
            data.put("email", user.get().getEmail());
            data.put("avatar", user.get().getAvatar());
            data.put("receiver", allReceiver);

            res.put("code", 200);
            res.put("msg", "用户信息返回成功");
            res.put("date", data);

            return res;
        }
        else
        {
            res.put("code", 404);
            res.put("msg", "用户不存在");
            return res;
        }
    }

}
