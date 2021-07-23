package com.hippo.fresh;

import com.hippo.fresh.config.ScheduleConfig;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 启动类
 */
@SpringBootApplication
public class FreshApplication {

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Autowired
    SearchProductRepository searchProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SearchSuggestionRepository searchSuggestionRepository;

    public static void main(String[] args) {

        SpringApplication.run(FreshApplication.class, args);
    }
////	将elasticsearch中的内容全部清空
//	@PreDestroy
//	public void deleteIndex() {
//		elasticsearchOperations.indexOps(SearchProduct.class).delete();
//	}
//
//	从数据库中读出商品信息写到ES中
//	@PostConstruct
//	public void buildIndex() {
//		elasticsearchOperations.indexOps(SearchProduct.class).refresh();
//		searchProductRepository.deleteAll();
//		searchProductRepository.saveAll(ScheduleConfig.ConvertProduct(productRepository.findAll()));
//	}

    //把搜索建议传到ES
//    @PostConstruct
//    public void BuildSuggestion() throws IOException {
//        ReadSearchSuggestion readSearchSuggestion = new ReadSearchSuggestion();
//        elasticsearchOperations.indexOps(SearchSuggestion.class).refresh();
//        searchProductRepository.deleteAll();
//        searchSuggestionRepository.saveAll(readSearchSuggestion.Read());
//    }

}
