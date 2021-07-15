package com.hippo.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.exception.ServerInternalErrorException;
import com.hippo.fresh.service.ProductService;
import com.hippo.fresh.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    //单个商品显示接口
    @GetMapping("/api/product/getone")
    public ResponseUtils getSomeInformationById(@RequestBody String idStr) {
        Long id = Long.valueOf(idStr);//先转换为string,再转换为Long
        return productService.findSomeInformationById(id);
    }

    //所有商品显示接口
    @GetMapping("/api/product/getall")
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
}
