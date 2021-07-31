package com.hippo.fresh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.ReceiverRepository;
import com.hippo.fresh.dao.UserRepository;
import com.hippo.fresh.entity.Receiver;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.service.ReceiverService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReceiverServiceImpl  implements ReceiverService {

    @Autowired
    private ReceiverRepository receiverRepository;

    @Autowired
    private UserRepository userRepository;


    /**根据用户id查找该用户所有收件人部分信息：id、name、address、phone */
    public List<Map<String,Object>> findSomeInformationByUserId(Long id){
        return receiverRepository.findSomeInformationByUserId(id);
    }


    /**根据收件人信息增加收件人*/
    public ResponseUtils save(Long userId,String name, String phone, String province, String city,
                              String district, String address){

        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());

        Receiver  receiver = receiverRepository.save(new Receiver(userId,name,phone,province,city,district,address,
                createTime,updateTime));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",receiver.getId());
        jsonObject.put("name",receiver.getName());
        jsonObject.put("phone",receiver.getPhone());
        jsonObject.put("province",receiver.getProvince());
        jsonObject.put("city",receiver.getCity());
        jsonObject.put("district",receiver.getDistrict());
        jsonObject.put("address",receiver.getAddress());
        return ResponseUtils.response(200, "收件人添加成功", jsonObject);
    }

    /**根据收件人id删除收件人*/
    public ResponseUtils delete(Long userId,Long receiverId){
        JSONObject jsonObject = new JSONObject();

       Optional<User> user = userRepository.findById(userId);

       //用户id存在
       if(user.isPresent()){
           Optional<Receiver> receiver = receiverRepository.findById(receiverId);
           //收件人id存在
           if(receiver.isPresent()){
               //收件人对应的用户id 与 token解析出来的用户id 保持一致
               if(userId == receiver.get().getUserId()){
                   receiverRepository.deleteById(receiverId);
                   return ResponseUtils.response(200, "收件人删除成功", jsonObject);
               }
               else
               {
                   return ResponseUtils.response(404, "收件人删除失败", jsonObject);
               }
           }
           else{
               return ResponseUtils.response(404, "收件人删除失败", jsonObject);
           }
       }
       else{
           jsonObject.put("id",userId);
           throw new UserNotExistException(jsonObject);
       }
    }
}
