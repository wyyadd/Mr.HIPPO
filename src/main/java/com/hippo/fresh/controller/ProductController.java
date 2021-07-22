package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.CommentRepository;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.dao.UserRepository;
import com.hippo.fresh.entity.Comment;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.entity.User;
import com.hippo.fresh.exception.ProductNotExistException;
import com.hippo.fresh.search.SearchProduct;
import com.hippo.fresh.exception.ServerInternalErrorException;
import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import com.hippo.fresh.service.ProductService;
import com.hippo.fresh.search.SearchProductService;
import com.hippo.fresh.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private JSONObject jsonObject;

    //单个商品显示接口
    @PostMapping("/api/product/getone")
    public ResponseUtils getSomeInformationById(@RequestBody JSONObject jsonObject) {
        Long id = jsonObject.getLong("id");
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
    @PostMapping("/api/product")
    public ResponseUtils ProductList(@RequestBody String jsStr){
        //default paras
        int pageNum = 6;
//        String productName = null; Integer sort = 1;
//        Integer order = 1; Integer upperBound = -1; Integer lowerBound = -1;
        String categoryFirst = null; String categorySecond = null;
        //获取相关参数
        jsonObject = JSON.parseObject(jsStr);
        if(jsonObject.getInteger("pageNum") != null)
            pageNum = jsonObject.getInteger("pageNum");
//        if(jsonObject.getString("product-name") != null)
//            productName = jsonObject.getString("product-name");
        if(jsonObject.getString("categoryFirst") != null)
            categoryFirst = jsonObject.getString("categoryFirst");
        if(jsonObject.getString("categorySecond") != null)
            categorySecond = jsonObject.getString("categorySecond");
//        if(jsonObject.getInteger("sort") != null)
//            sort = jsonObject.getInteger("sort");
//        if(jsonObject.getInteger("order") != null)
//            order = jsonObject.getInteger("order");
//        if(jsonObject.getInteger("upper-bound") != null)
//            upperBound = jsonObject.getInteger("upper-bound");
//        if(jsonObject.getInteger("lower-bound") != null)
//            lowerBound = jsonObject.getInteger("lower-bound");
        log.info(categoryFirst);
        log.info(categorySecond);
        return productService.GetProductList(pageNum,null,categoryFirst, categorySecond,1,1,-1,-1);
    }

    //推荐商品接口
    @PostMapping("/api/product/recommend")
    public ResponseUtils RecommendProductList(@RequestBody String jsStr){
        if(jsStr == null){
            throw new ProductNotExistException(null);
        }
        //获取相关参数
        JSONObject jsonObject = JSON.parseObject(jsStr);
        int page = (jsonObject.getInteger("page") == null ? 0 : jsonObject.getInteger("page"));
        int pageNum = (jsonObject.getInteger("page-num") == null ? 10 : jsonObject.getInteger("page-num"));
        String productName = jsonObject.getString("product-name");
//        int type = (jsonObject.getInteger("category_id") == null ? 0 : jsonObject.getInteger("category_id"));
        List<SearchProduct> products = searchProductService.processSearch(page,pageNum,productName,1,0,Integer.MAX_VALUE,0);
        return ResponseUtils.success("查找成功",products);
    }

    //模糊搜索接口
    @PostMapping("/api/product/search")
    public ResponseUtils Search(@RequestBody String jsStr){
        if(jsStr == null){
            throw new ProductNotExistException(null);
        }
        jsonObject = JSON.parseObject(jsStr);
        int page = (jsonObject.getInteger("page") == null ? 0 : jsonObject.getInteger("page"));
        int pageNum = (jsonObject.getInteger("page-num") == null ? 10 : jsonObject.getInteger("page-num"));
        String productName = jsonObject.getString("product-name");
//        int type = (jsonObject.getInteger("category_id") == null ? 0 : jsonObject.getInteger("category_id"));
        int sort = (jsonObject.getInteger("sort") == null ? 1 : jsonObject.getInteger("sort"));
        int order = (jsonObject.getInteger("order") == null ? 0 : jsonObject.getInteger("order"));
        int upperBound = (jsonObject.getInteger("upper-bound") == null ? Integer.MAX_VALUE : jsonObject.getInteger("upper-bound"));
        int lowerBound = (jsonObject.getInteger("lower-bound") == null ? 0 : jsonObject.getInteger("lower-bound"));
        List<SearchProduct> products = searchProductService.processSearch(page,pageNum,productName,sort,order,upperBound,lowerBound);
        return ResponseUtils.success("查找成功",products);
    }

    //通过产品id获得评论接口
    @PostMapping("/api/product/comment")
    public ResponseUtils GetComments(@RequestBody String jsonStr){
        jsonObject = JSON.parseObject(jsonStr);
        return productService.findCommentByProductId(jsonObject.getLong("productId"));
    }

    //测试接口
    @GetMapping("/api/product/test")
    public ResponseUtils GetComments(@RequestBody JSONObject jsonObject){
        Long id = jsonObject.getLong("id");
        Double price = jsonObject.getDouble("price");
        Product p = productRepository.getById(id);
        p.setPrice(price);
        productRepository.save(p);
        return ResponseUtils.response(200,"修改价格成功",jsonObject);
    }


}
