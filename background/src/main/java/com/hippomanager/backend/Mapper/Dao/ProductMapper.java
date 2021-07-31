package com.hippomanager.backend.Mapper.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hippomanager.backend.Mapper.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {

}
