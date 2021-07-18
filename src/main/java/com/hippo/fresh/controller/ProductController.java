package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.search.SearchProduct;
import com.hippo.fresh.exception.ServerInternalErrorException;
import com.hippo.fresh.service.ProductService;
import com.hippo.fresh.search.SearchProductService;
import com.hippo.fresh.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@Slf4j
public class ProductController {

    private ProductService productService;

    private SearchProductService searchProductService;

    @Autowired
    public ProductController(SearchProductService searchProductService, ProductService productService){
        this.searchProductService = searchProductService;
        this.productService = productService;
    }

    private JSONObject jsonObject;

    //单个商品显示接口
    @PostMapping("/api/product/getone")
    public ResponseUtils getSomeInformationById(@RequestBody String idStr) {
        Long id = Long.valueOf(idStr);//先转换为string,再转换为Long
        return productService.findSomeInformationById(id);
    }

    //所有商品显示接口
    @PostMapping("/api/product/getall")
    public ResponseUtils findAllById(@RequestBody Map<String,Object> map) {
        List<Long> LongIds = null;
        try {
            List<Object> ids = (List<Object>) map.get("ids");
            LongIds = new ArrayList<>();
            for (Object id : ids) {
                LongIds.add(Long.valueOf(id.toString()));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("/api/product/getall, findAllById Error");
            throw new ServerInternalErrorException(null);
        }
        return productService.findAllById(LongIds);
    }

    //商品列表显示接口
    @PostMapping("/api/product-list")
    public ResponseUtils ProductList(@RequestBody String jsStr){
        //default paras
        int page = 0; int pageNum = 10;
        String productName = null; Integer type = 0; Integer sort = 1;
        Integer order = 1; Integer upperBound = -1; Integer lowerBound = -1;
        //获取相关参数
        jsonObject = JSON.parseObject(jsStr);
        if(jsonObject.getInteger("page") != null)
            page = jsonObject.getInteger("page");
        if(jsonObject.getInteger("page-num") != null)
            pageNum = jsonObject.getInteger("page-num");
        if(jsonObject.getString("product-name") != null)
            productName = jsonObject.getString("product-name");
        if(jsonObject.getInteger("type") != null)
            type = jsonObject.getInteger("type");
        if(jsonObject.getInteger("sort") != null)
            sort = jsonObject.getInteger("sort");
        if(jsonObject.getInteger("order") != null)
            order = jsonObject.getInteger("order");
        if(jsonObject.getInteger("upper-bound") != null)
            upperBound = jsonObject.getInteger("upper-bound");
        if(jsonObject.getInteger("lower-bound") != null)
            lowerBound = jsonObject.getInteger("lower-bound");
        return productService.GetProductList(page,pageNum,productName,type,sort,order,upperBound,lowerBound);
    }

    //推荐商品接口
    @PostMapping("/api/product/recommend")
    public ResponseUtils RecommendProductList(@RequestBody String jsStr){
        //获取相关参数
        JSONObject jsonObject = JSON.parseObject(jsStr);
        int page = (jsonObject.getInteger("page") == null ? 0 : jsonObject.getInteger("page"));
        int pageNum = (jsonObject.getInteger("page-num") == null ? 10 : jsonObject.getInteger("page-num"));
        String productName = jsonObject.getString("product-name");
        int type = (jsonObject.getInteger("category_id") == null ? 0 : jsonObject.getInteger("category_id"));
        List<SearchProduct> products = searchProductService.processSearch(page,pageNum,productName,type,1,1,Integer.MAX_VALUE,0);
        return ResponseUtils.success("查找成功",products);
    }

    //模糊搜索接口
    @PostMapping("/api/product/search")
    public ResponseUtils SearchTest(@RequestBody(required = false) String jsStr){
        JSONObject jsonObject = JSON.parseObject(jsStr);
        int page = (jsonObject.getInteger("page") == null ? 0 : jsonObject.getInteger("page"));
        int pageNum = (jsonObject.getInteger("page-num") == null ? 10 : jsonObject.getInteger("page-num"));
        String productName = jsonObject.getString("product-name");
        int type = (jsonObject.getInteger("category_id") == null ? 0 : jsonObject.getInteger("category_id"));
        int sort = (jsonObject.getInteger("sort") == null ? 1 : jsonObject.getInteger("sort"));
        int order = (jsonObject.getInteger("order") == null ? 1 : jsonObject.getInteger("order"));
        int upperBound = (jsonObject.getInteger("upper-bound") == null ? Integer.MAX_VALUE : jsonObject.getInteger("upper-bound"));
        int lowerBound = (jsonObject.getInteger("lower-bound") == null ? 0 : jsonObject.getInteger("lower-bound"));
        List<SearchProduct> products = searchProductService.processSearch(page,pageNum,productName,type,sort,order,upperBound,lowerBound);
        return ResponseUtils.success("查找成功",products);
    }

    //搜索框下方推荐接口
    @PostMapping("/api/product/suggestion")
    public List<String> SearchSuggestion(@RequestParam(value = "query") String query){
        List<String> suggestions = searchProductService.fetchSuggestions(query);
        return suggestions;
    }
}
