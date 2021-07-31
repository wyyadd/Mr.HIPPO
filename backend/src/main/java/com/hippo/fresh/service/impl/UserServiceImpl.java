package com.hippo.fresh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.CommentRepository;
import com.hippo.fresh.dao.ReceiverRepository;
import com.hippo.fresh.dao.UserRepository;
import com.hippo.fresh.dao.VerificationRepository;
import com.hippo.fresh.entity.Comment;
import com.hippo.fresh.entity.Receiver;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.entity.Verification;
import com.hippo.fresh.exception.CommentNotExistException;
import com.hippo.fresh.exception.UserHasExistException;
import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Signature;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private CommentRepository commentRepository;

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


    /** 判断验证码是否过期：5分钟 */
    public boolean verCodeInvalid(Timestamp createTime, Timestamp nowTime) {
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        long t1 = createTime.getTime();
        long t2 = nowTime.getTime();
        int hours=(int) ((t2 - t1)/(1000*60*60));
        int minutes=(int) (((t2 - t1)/1000-hours*(60*60))/60);
//        int second=(int) ((t2 - t1)/1000-hours*(60*60)-minutes*60);
        if(minutes >= 5)
            return true;
        else
            return false;
    }

    /** 用户注册
     * @return json
     * */
    public ResponseUtils register(String username, String password,String email,String verCode,Long verCodeId) {

        Optional<Verification> verification = verificationRepository.findById(verCodeId);

        //用户没有点击获取验证码，前端传入的verCodeId = 0
        if(!verification.isPresent()){
            return ResponseUtils.response(401,"请获取验证码后再进行登录", jsonObject);
        }
        //用户已获取验证码
        else{
            if(verCodeInvalid(verification.get().getCreateTime(),new Timestamp(System.currentTimeMillis()))){
                return ResponseUtils.response(402,"验证码过期，请重新获取验证码", jsonObject);
            }
            else{
                //验证码错误
                if(!verCode.equals(verification.get().getVerCode())){
                    return ResponseUtils.response(403,"验证码错误，请重新输入验证码", jsonObject);
                }
                else{
                    Optional<User> user = userRepository.findByUsername(username);

                    boolean existsUser = user.isPresent();
                    //用户名已存在
                    if (existsUser) {
                        jsonObject = new JSONObject();
                        throw new UserHasExistException(jsonObject);
                    }
                    //验证码正确，用户名不存在，成功注册用户
                    else {
                        Timestamp createTime = new Timestamp(System.currentTimeMillis());
                        User newUser = userRepository.save(new User(username,bCryptPasswordEncoder.encode(password),email,createTime));
                        jsonObject = new JSONObject();
                        return ResponseUtils.response(200,"注册成功", jsonObject);
                    }
                }

            }
        }
    }

    /** 用户主页 */
    public ResponseUtils information(Long userId){
        jsonObject = new JSONObject();

        Optional<User> u = userRepository.findById(userId);

        if(u.isPresent()) {
            User user = u.get();
            List<Receiver> allReceiver = receiverRepository.findAllByUserIdOrderByUpdateTimeDesc(user.getId());
            jsonObject.put("user",user);
            jsonObject.put("receiver", allReceiver);
//            jsonObject.put("id", user.getId());
//            jsonObject.put("username", user.getUsername());
//            jsonObject.put("email", user.getEmail());
//            jsonObject.put("phone", user.getPhone());
//            jsonObject.put("avatar", user.getAvatar());
//            jsonObject.put("gender", user.getGender());
//            jsonObject.put("age", user.getAge());
//            jsonObject.put("foodPreference", user.getFoodPreference());
//            jsonObject.put("signature", user.getSignature());
//            jsonObject.put("hometown", user.getHometown());
//            jsonObject.put("residentArea", user.getResidentArea());
//            jsonObject.put("label", user.getLabel());
//            jsonObject.put("createTime", user.getCreateTime());

            return ResponseUtils.success("用户信息返回成功",jsonObject);
        }
        else
        {
            jsonObject.put("id",userId);
            throw new UserNotExistException(jsonObject);
        }
    }

    /** 修改用户密码 */
    public ResponseUtils passwordModify(Long userId,String oldPassword,String newPassword){

        jsonObject = new JSONObject();

        Optional<User> u = userRepository.findById(userId);
        if(!u.isPresent()){
            jsonObject.put("id",userId);
            throw new UserNotExistException(jsonObject);
        }
        //用户存在
        else {
            User user = u.get();
            String password = user.getPassword();
            //用户输入原始密码与数据库保持一致
            if(bCryptPasswordEncoder.matches(oldPassword,password)){
                user.setPassword(bCryptPasswordEncoder.encode(newPassword));
                userRepository.save(user);
                return ResponseUtils.response(200,"用户密码修改成功", jsonObject);
            }
            else{
                return ResponseUtils.response(404,"用户密码修改失败", jsonObject);
            }
        }
    }

    /** 修改用户个人信息*/
    public ResponseUtils informationModify(Long userId,String email,String phone,String gender,Long age,
                                           String foodPreference,String signature,String hometown,
                                           String residentArea,String label){
        jsonObject = new JSONObject();

        Optional<User> u = userRepository.findById(userId);
        if(!u.isPresent()){
            jsonObject.put("id",userId);
            throw new UserNotExistException(jsonObject);
        }
        //用户id存在
        else {
            User user = u.get();

                user.setEmail(email);
                user.setPhone(phone);
                user.setGender(gender);
                user.setAge(age);
                user.setFoodPreference(foodPreference);
                user.setSignature(signature);
                user.setHometown(hometown);
                user.setResidentArea(residentArea);
                user.setLabel(label);
                userRepository.save(user);
                return ResponseUtils.response(200,"用户个人信息修改成功", jsonObject);
        }
    }

    @Override
    public ResponseUtils findCommentByUserId(Long userId) {
        List<Comment> comments = commentRepository.findByUserId(userId);
        if(!comments.isEmpty()){
            return ResponseUtils.success("查找成功", comments);
        }
        else {
            jsonObject.put("userId", userId);
            throw new CommentNotExistException(jsonObject);
        }
    }
}
