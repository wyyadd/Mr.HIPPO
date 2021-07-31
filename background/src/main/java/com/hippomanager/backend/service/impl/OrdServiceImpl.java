package com.hippomanager.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hippomanager.backend.Mapper.Dao.OrdMapper;
import com.hippomanager.backend.Mapper.entity.Ord;
import com.hippomanager.backend.Mapper.entity.Product;
import com.hippomanager.backend.service.IOrdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdServiceImpl implements IOrdService {
    @Autowired
    OrdMapper ordMapper;
    @Override
    public List<Ord> selectOrdAll() {
        Wrapper<Ord> wrapper =new QueryWrapper();
        return ordMapper.selectList(wrapper);
    }
}
