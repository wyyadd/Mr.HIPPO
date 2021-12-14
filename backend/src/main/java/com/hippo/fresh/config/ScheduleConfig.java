package com.hippo.fresh.config;

import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.search.SearchProduct;
import com.hippo.fresh.search.SearchProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Autowired
    SearchProductRepository searchProductRepository;

    @Autowired
    ProductRepository productRepository;

    //every two hour to update index
//    @Scheduled(cron = "0 0 0/12 * * *")
    public void ElasticSearchSchedule(){
        searchProductRepository.deleteAll();
        searchProductRepository.saveAll(ConvertProduct(productRepository.findAll()));
    }

    //将商品类转换成搜索商品类（放在ES中）
    public static List<SearchProduct> ConvertProduct(List<Product> products){
        List<SearchProduct> searchProducts = new ArrayList<>();
        for(Product p : products){
            searchProducts.add(SearchProduct.builder()
                    .id(String.valueOf(p.getId()))
                    .productId(p.getId())
                    .categoryFirst(p.getCategoryFirst())
                    .categorySecond(p.getCategorySecond())
                    .name(p.getName())
                    .price(p.getPrice())
                    .detail(p.getDetail())
                    .status(p.getStatus())
                    .pictureUrl(p.getPictureUrl())
                    .score(p.getScore())
                    .salesAmount(p.getSalesAmount()).build());
        }
        return searchProducts;
    }
}
