package com.hippo.fresh.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.CommentRepository;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Comment;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.exception.CommentNotExistException;
import com.hippo.fresh.exception.ProductNotExistException;
import com.hippo.fresh.exception.ServerInternalErrorException;
import com.hippo.fresh.service.ProductService;
import com.hippo.fresh.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ProductServiceImpl  implements ProductService  {

    @Autowired
    private ProductRepository productRepository;

    private JSONObject jsonObject;

    @Autowired
    private CommentRepository commentRepository;

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
    public ResponseUtils GetProductList(int pageNum, String productName, String categoryFirst, String categorySecond, int sort, int order, int upperBound, int lowerBound) {
//        List<Product> data = productRepository.findAll(ProductRepository.getSpec(productName, type, sort, order, upperBound,lowerBound));
        Page<Product> rawData = productRepository.findAll(ProductRepository.getSpec(productName, categoryFirst, categorySecond, sort, order, upperBound,lowerBound),
                PageRequest.of(0,pageNum));
        //get num of page
        int num = rawData.getTotalPages();
        if(num == 0)
            num++;
        //get random page
        Page<Product> data = productRepository.findAll(ProductRepository.getSpec(productName, categoryFirst, categorySecond, sort, order, upperBound,lowerBound),
                PageRequest.of(new Random().nextInt(num) - 1 ,pageNum));
        //get json array
        if(!data.isEmpty()){
            JSONArray jsonArray = new JSONArray();
            for(Product it : data){
                jsonObject = new JSONObject();
                jsonObject.put("id",it.getId());
                jsonObject.put("name",it.getName());
                jsonObject.put("pictureUrl",it.getPictureUrl());
                jsonObject.put("price",it.getPrice());
                jsonObject.put("sales_amount",it.getSalesAmount());
                jsonArray.add(jsonObject);
            }
            return ResponseUtils.success("查找成功",jsonArray);
        }else {
            throw new ProductNotExistException(null);
        }
    }

    @Override
    public ResponseUtils findCommentByProductId(Long productId) {
        List<Comment> comments = commentRepository.findByProductId(productId);
        if(!comments.isEmpty()){
            return ResponseUtils.success("查找成功", comments);
        }
        else{
            jsonObject.put("productId",productId);
            throw new CommentNotExistException(jsonObject);
        }
    }

    @Override
    public ResponseUtils CreateComment(Comment comment) {
        try{
            commentRepository.save(comment);
            return ResponseUtils.success("保存评论成功", null);
        }catch (Exception e){
            throw new ServerInternalErrorException(null);
        }
    }
}
