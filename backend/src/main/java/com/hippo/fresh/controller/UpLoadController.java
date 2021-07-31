package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.UserRepository;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class UpLoadController {


    @Autowired
    private UserRepository userRepository;

    //时间格式化
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

   //图片保存路径，自动从yml文件中获取数据
   //示例： D:/upload/

    //本地地址
    @Value("${file.localurl}")
    private String localPath;

    //服务器地址
    @Value("${file.uploadurl}")
    private String uploadPath;

    @PostMapping("/api/user/upload")
    public ResponseUtils upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        //从token中获取id
        String token = request.getHeader(JWTConfig.tokenHeader);
        Long userId = JWTTokenUtil.parseAccessToken(token).getId();


        //1.文件保存地址的后半段目录：  2021/07/18/
        String directory = simpleDateFormat.format(new Date());

        //2.文件保存目录  D:/upload/2021/07/18/   如果目录不存在，则创建
        File dir = new File(uploadPath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //3.文件保存名称（产生的唯一随机数+文件后缀）
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;

        //4.创建这个新文件
        File newFile = new File(uploadPath + directory + newFileName);

        //5.文件上传
        try {
            file.transferTo(newFile);
            //可访问url格式：  协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload/" + directory + newFileName;
            jsonObject.put("url",url);

            //修改用户头像
            User user = userRepository.findById(userId).get();
            user.setAvatar(url);
            userRepository.save(user);

            return ResponseUtils.response(200, "文件上传成功",jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtils.response(404, "文件上传失败",jsonObject);
        }
    }
}