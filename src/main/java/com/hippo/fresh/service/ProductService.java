package com.hippo.fresh.service;

import java.util.List;
import java.util.Map;


public interface ProductService {

    //根据商品id返回商品的需要信息
    public Map<String,Object> findSomeInformationById(Long id);

    //根据商品id列表返回商品的信息
    public Map<String,Object> findAllById(List<Long> ids);

}
