package com.hippo.fresh;

import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.search.SearchProduct;
import com.hippo.fresh.search.SearchProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	public static void main(String[] args) {

		SpringApplication.run(FreshApplication.class, args);
	}

	//将elasticsearch中的内容全部清空
	@PreDestroy
	public void deleteIndex() {
		elasticsearchOperations.indexOps(SearchProduct.class).delete();
	}

	//从数据库中读出商品信息写到ES中
	@PostConstruct
	public void buildIndex() {
		elasticsearchOperations.indexOps(SearchProduct.class).refresh();
		searchProductRepository.deleteAll();
		searchProductRepository.saveAll(ConvertProduct(productRepository.findAll()));
	}

	//将商品类转换成搜索商品类（放在ES中）
	private List<SearchProduct> ConvertProduct(List<Product> products){
		List<SearchProduct> searchProducts = new ArrayList<>();
		int i = 0;
		for(Product p : products){
			searchProducts.add(SearchProduct.builder()
					.id(String.valueOf(i++))
					.productId(p.getId())
					.categoryId(p.getCategoryId())
					.name(p.getName())
					.price(p.getPrice())
					.detail(p.getDetail())
					.salesAmount(p.getSalesAmount()).build());
		}
		return searchProducts;
	}
}
