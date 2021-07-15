package com.hippo.fresh.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.exception.ProductNotExistException;
import com.hippo.fresh.service.ProductService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    private JSONObject jsonObject;

    //根据商品id返回商品的需要信息
    public ResponseUtils findSomeInformationById(Long id){

        //判断商品是否存在
        if(productRepository.existsById(id)) {
            return ResponseUtils.success("商品查找成功",productRepository.findSomeInformationById(id));
        }
        else
        {
            jsonObject = new JSONObject();
            jsonObject.put("id", id);
            throw new ProductNotExistException(jsonObject);
        }
    }

    //根据商品id列表返回商品信息
    public ResponseUtils findAllById(List<Long> ids){

        //id列表为空
        if(ids.isEmpty()){
            throw new ProductNotExistException(null);
        }

        //id列表中存在无效id
       for(Long id:ids) {
            if (!productRepository.existsById(id)) {
                throw new ProductNotExistException(null);
            }
        }

        //id列表商品均符合条件
        return ResponseUtils.success("商品查找成功",productRepository.findAllById(ids));
    }


}
