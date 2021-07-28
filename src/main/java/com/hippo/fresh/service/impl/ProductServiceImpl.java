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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        jsonObject = new JSONObject();
        //判断商品是否存在
        if(productRepository.findById(id).isPresent()) {
            Product product = productRepository.findById(id).get();
            jsonObject.put("id",product.getId());
            jsonObject.put("categoryFirst",product.getCategoryFirst());
            jsonObject.put("categorySecond",product.getCategorySecond());
            jsonObject.put("name",product.getName());
            jsonObject.put("pictureUrlOne",product.getPictureUrl());
            jsonObject.put("pictureUrlTwo",product.getPictureUrlTwo());
            jsonObject.put("pictureUrlThree",product.getPictureUrlThree());
            jsonObject.put("pictureUrlFour",product.getPictureUrlFour());
            jsonObject.put("originPlace",product.getOriginPlace());
            jsonObject.put("price",product.getPrice());
            jsonObject.put("stock",product.getStock());
            jsonObject.put("detail",product.getDetail());
            jsonObject.put("status",product.getStatus());
            jsonObject.put("salesAmount",product.getSalesAmount());
            return ResponseUtils.response(200,"商品信息查询成功",jsonObject);
        }
        else {
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
            x.put("score",product.getScore());
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
        Page<Product> data;
        int num = rawData.getTotalPages();
        //get random page
        if(num == 0){
//            log.error("num = 0, error, none");
            throw new ProductNotExistException(null);
        }
        if(num == 1)
            data = rawData;
        else
            data = productRepository.findAll(ProductRepository.getSpec(productName, categoryFirst, categorySecond, sort, order, upperBound,lowerBound),
                    PageRequest.of(new Random().nextInt(num-1),pageNum));
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
                jsonObject.put("score",it.getScore());
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

    //获取秒杀商品列表
    @Override
    public ResponseUtils GetKillProductList() {
        List<Product> products = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        for(long i : new long[]{109770, 108196, 165775, 161655}){
            JSONObject jsonObject = new JSONObject();
            Product product = productRepository.getById(i);
            jsonObject.put("name", product.getName());
            jsonObject.put("id", product.getId());
            jsonObject.put("pictureUrl", product.getPictureUrl());
            jsonObject.put("originPrice",product.getPrice());
            jsonObject.put("currentPrice", product.getPrice()*0.3);
            jsonObject.put("time", "2021-07-30 20:00:00");
            jsonArray.add(jsonObject);
        }
        jsonArray.getJSONObject(0).put("days", 45);
        jsonArray.getJSONObject(1).put("days", 63);
        jsonArray.getJSONObject(2).put("days", 32);
        jsonArray.getJSONObject(3).put("days", 93);
        jsonArray.getJSONObject(0).put("percentage", 45);
        jsonArray.getJSONObject(1).put("percentage", 25);
        jsonArray.getJSONObject(2).put("percentage", 98);
        jsonArray.getJSONObject(3).put("percentage", 85);
        return ResponseUtils.success("查找秒杀商品成功", jsonArray);
    }
}
