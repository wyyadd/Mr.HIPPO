package com.hippomanager.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hippomanager.backend.Mapper.entity.Product;
import java.util.List;

public interface IProductService {
    int addProduct(Product product);
    List<Product> selectProductAll();
    int deleteProduct(long id);
    int updateProductById(Product product);
    Product selectProductById(long id);
    IPage<Product> GetPageProduct(int pageNum);
}
