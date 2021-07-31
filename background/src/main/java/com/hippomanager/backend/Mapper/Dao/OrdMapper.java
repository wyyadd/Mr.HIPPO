package com.hippomanager.backend.Mapper.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hippomanager.backend.Mapper.entity.Ord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrdMapper extends BaseMapper<Ord> {
}
