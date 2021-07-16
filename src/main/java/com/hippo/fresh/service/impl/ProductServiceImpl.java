package com.hippo.fresh.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.exception.ProductNotExistException;
import com.hippo.fresh.service.ProductService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl  implements ProductService  {

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
        JSONObject jsonObject = new JSONObject();

        //id列表为空
        if(ids.isEmpty()){
            throw new ProductNotExistException(jsonObject);
        }

        //id列表中存在无效id
        for(Long id:ids) {
            if (!productRepository.existsById(id)) {
                throw new ProductNotExistException(jsonObject);
            }
        }
        //id列表商品均符合条件
        List<Map<String,Object>> products = new ArrayList<>();

        for(Long id:ids){
            Product product = productRepository.findById(id).get();
            Map<String, Object> x = new HashMap<>();
            x.put("pictureUrl",product.getPictureUrl());
            x.put("name",product.getName());
            x.put("price",product.getPrice());
            x.put("details",product.getDetail());
            x.put("id",product.getId());
            products.add(x);
        }

        jsonObject.put("products",products);
        return ResponseUtils.response(200, "商品查找成功", jsonObject);
//        //id列表为空
//        if(ids.isEmpty()){
//            throw new ProductNotExistException(null);
//        }
//
//        //id列表中存在无效id
//       for(Long id:ids) {
//            if (!productRepository.existsById(id)) {
//                throw new ProductNotExistException(null);
//            }
//        }
//
//        //id列表商品均符合条件
//        return ResponseUtils.success("商品查找成功",productRepository.findAllById(ids));
    }


    //根据参数获取商品列表
    @Override
    public ResponseUtils GetProductList(int page, int pageNum, String productName, int type, int sort, int order, int upperBound, int lowerBound) {
//        List<Product> data = productRepository.findAll(ProductRepository.getSpec(productName, type, sort, order, upperBound,lowerBound));
        Page<Product> data = productRepository.findAll(ProductRepository.getSpec(productName, type, sort, order, upperBound,lowerBound),PageRequest.of(page,pageNum));
        if(!data.isEmpty()){
            JSONArray jsonArray = new JSONArray();
            for(Product it : data){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",it.getId());
                jsonObject.put("name",it.getName());
                jsonObject.put("picture",it.getPictureUrl());
                jsonObject.put("price",it.getPrice());
                jsonObject.put("sales_amount",it.getSalesAmount());
                jsonArray.add(jsonObject);
            }
            return ResponseUtils.success(" 查找成功",jsonArray);
        }else {
            throw new ProductNotExistException(null);
        }
    }
}
