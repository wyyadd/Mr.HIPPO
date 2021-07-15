package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProductRepository  extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product> {

    //根据id判断商品是否存在
    @Override
    boolean existsById(Long aLong);

    //根据单个商品id返回商品的需要信息
    @Query(value = "select id,name,picture,picture_url,price,stock,detail,status,sales_amount from product x where x.id =?1 ",nativeQuery = true)
    Map<String,Object> findSomeInformationById(Long id);

    @Override
    List<Product> findAllById(Iterable<Long> iterable);
}
