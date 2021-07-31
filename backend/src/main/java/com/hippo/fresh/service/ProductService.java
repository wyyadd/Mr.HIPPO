package com.hippo.fresh.service;

import com.hippo.fresh.entity.Comment;
import com.hippo.fresh.utils.ResponseUtils;

import java.util.List;
import java.util.Map;


public interface ProductService {

    //根据商品id返回商品的需要信息
    public ResponseUtils findSomeInformationById(Long id);

    //根据商品id列表返回商品的信息
    public ResponseUtils findAllById(List<Long> ids);

    //根据商品名商品类型排序方式返回商品列表
    public ResponseUtils GetProductList(int pageNum, String productName, String categoryFirst, String categorySecond, int sort, int order, int upperBound,  int lowerBound);

    public ResponseUtils findCommentByProductId(Long productId);

    public ResponseUtils CreateComment(Comment comment);

    public ResponseUtils GetKillProductList();

}
