package com.hippomanager.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hippomanager.backend.Mapper.Dao.ProductMapper;
import com.hippomanager.backend.Mapper.entity.Product;
import com.hippomanager.backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.ProcessingInstruction;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public int addProduct(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public List<Product> selectProductAll() {
        Wrapper<Product> wrapper =new QueryWrapper();
        return productMapper.selectList(wrapper);
    }

    @Override
    public int deleteProduct(long id) {
        return productMapper.deleteById(id);
    }


    @Override
    public int updateProductById(Product product) {
        return productMapper.updateById(product);
    }

    @Override
    public Product selectProductById(long id) {
        return productMapper.selectById(id);
    }

    @Override
    public IPage<Product> GetPageProduct(int pageNum) {
        Page<Product> userPage = new Page<>(pageNum, 10);
        return productMapper.selectPage(userPage, null);
    }
}
