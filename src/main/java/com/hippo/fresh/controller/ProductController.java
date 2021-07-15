package com.hippo.fresh.controller;

import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    //测试接口
    @GetMapping("/test")
    public Map<String,Object> test(@RequestBody HashMap<String,Object> map) {

        Long id = Long.valueOf(map.get("id").toString());//先转换为string,再转换为Long

        Map<String, Object> res = new HashMap<>();
        if(productRepository.existsById(id)) {
            res.put("key", "success");
        }
        else
        {
            res.put("key", "false");
        }
        return res;
    }

    //测试接口
    @GetMapping("/test1")
    public Map<String,Object> test1() {
        Map<String, Object> res = new HashMap<>();
        res.put("key","success!!!");
         return res;
    }


    //单个商品显示接口
    @GetMapping("/getone")
    public Map<String,Object> getSomeInformationById(@RequestBody HashMap<String,Object> map) {

        Long id = Long.valueOf(map.get("id").toString());//先转换为string,再转换为Long
        return productService.findSomeInformationById(id);
    }

    //所有商品显示接口
    @GetMapping("/getall")
    public Map<String,Object> findAllById(@RequestBody HashMap<String,Object> map) {

    List<Object> ids= (List<Object>)map.get("ids");

    List<Long> Longids = new ArrayList<>();
    for(Object id:ids){
        Longids.add(Long.valueOf(id.toString()));
    }

    return productService.findAllById(Longids);

    }



}
