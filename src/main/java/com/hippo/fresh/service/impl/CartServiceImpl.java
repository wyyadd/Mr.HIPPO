package com.hippo.fresh.service.impl;

import com.hippo.fresh.dao.CartRepository;
import com.hippo.fresh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl  implements CartService{

    @Autowired
    private CartRepository cartRepository;

    public List<Map<String,Object>> findCartInformationByUserId(Long id){
        return cartRepository.findCartInformationByUserId(id);
    }
}
