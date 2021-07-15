package com.hippo.fresh.service.impl;


import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    //根据商品id返回商品的需要信息
    public Map<String,Object> findSomeInformationById(Long id){

        Map<String, Object> res = new HashMap<>();

        //判断商品是否存在
        if(productRepository.existsById(id)) {

            res.put("code", 200);
            res.put("msg", "商品查找成功");
            res.put("data",productRepository.findSomeInformationById(id));
        }
        else
        {
            res.put("code", 404);
            res.put("msg", "商品查找失败");
        }

        return res;
    }

    //根据商品id列表返回商品信息
    public Map<String,Object> findAllById(List<Long> ids){
        Map<String, Object> res = new HashMap<>();

        //id列表为空
        if(ids.isEmpty()){
            res.put("code", 404);
            res.put("msg", "商品查找失败");
            return res;
        }

        //id列表中存在无效id
       for(Long id:ids) {
            if (!productRepository.existsById(id)) {
                res.put("code", 404);
                res.put("msg", "商品查找失败");
                return res;
            }
        }

        //id列表商品均符合条件
        res.put("code", 200);
        res.put("msg", "商品查找成功");
        res.put("data", productRepository.findAllById(ids));
        return res;
    }


}
