package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.dao.UserRepository;
import com.hippo.fresh.entity.Comment;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.ProductService;
import com.hippo.fresh.service.UserService;
import com.hippo.fresh.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private JSONObject jsonObject;

    //用户注册接口
    @PostMapping("/register")
    public ResponseUtils register(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("email");
        String verCode = jsonObject.getString("verCode");
        Long verCodeId = jsonObject.getLong("verCodeId");
        return userService.register(username,password,email,verCode,verCodeId);
    }

    //用户主页接口
    @PostMapping("/information")

    public ResponseUtils information(HttpServletRequest request) {
        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long id = JWTTokenUtil.parseAccessToken(token).getId();

        return userService.information(id);
    }


    //用户修改密码接口
    @PostMapping("/password")
    public ResponseUtils password(@RequestBody String jsStr,HttpServletRequest request) {

        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        JSONObject jsonObject = JSON.parseObject(jsStr);
        String oldPassword = jsonObject.getString("oldPassword");
        String newPassword = jsonObject.getString("newPassword");

//        System.out.println(oldPassword);
//        System.out.println(newPassword);
        return userService.passwordModify(userId,oldPassword,newPassword);
    }


    //用户修改个人资料接口
    @PostMapping("/information/modify")
    public ResponseUtils informationModify(@RequestBody String jsStr,HttpServletRequest request) {

        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();

        JSONObject jsonObject = JSON.parseObject(jsStr);
        String email = jsonObject.getString("email");
        String phone = jsonObject.getString("phone");
        String gender = jsonObject.getString("gender");
        Long age = jsonObject.getLong("age");
        String foodPreference = jsonObject.getString("foodPreference");
        String signature = jsonObject.getString("signature");
        String hometown = jsonObject.getString("hometown");
        String residentArea = jsonObject.getString("residentArea");
        String label = jsonObject.getString("label");

        return userService.informationModify(userId,email,phone,gender,age,foodPreference,
                signature,hometown,residentArea,label);
    }

    @PostMapping("/comment")
    public ResponseUtils GetUserComment(HttpServletRequest request){
        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();
        return userService.findCommentByUserId(userId);
    }

    //写评论接口
    @PostMapping("/makecomment")
    public ResponseUtils MakeComments(@RequestBody String jsonStr, HttpServletRequest request){
        jsonObject = JSON.parseObject(jsonStr);
        //获取用户相关信息
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();
        User user = userRepository.findById(userId).get();
        String username = user.getUsername();
        String userAvatar = user.getAvatar();
        //获取商品相关信息
        String comment = jsonObject.getString("comment");
        Long productId = jsonObject.getLong("productId");
        Product product = productRepository.getById(productId);
        String productUrl = product.getPictureUrl();
        String productName = product.getName();
        int star = jsonObject.getInteger("star") == null ? 5 : jsonObject.getInteger("star");
        //重新计算商品评分，并保存
        long commentNUm = product.getCommentNum() == null ? 0 : product.getCommentNum();
        ++commentNUm;
        long commentScore = product.getCommentScore() == null ? 0 : product.getCommentScore();
        commentScore += star;
        product.setCommentNum(commentNUm);
        product.setCommentScore(commentScore);
        product.setScore((double) (commentScore/commentNUm));
        productRepository.save(product);
        //创建评论
        Comment comments = Comment.builder()
                .comment(comment).createTime(new Timestamp(System.currentTimeMillis()))
                .productId(productId).productUrl(productUrl).productName(productName)
                .userId(userId).username(username).userAvatar(userAvatar).star(star)
                        .build();
        return productService.CreateComment(comments);
    }
}
