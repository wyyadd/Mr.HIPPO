package com.test.java.com.hippo.fresh;

import com.hippo.fresh.search.SearchProduct;
import com.hippo.fresh.search.SearchProductRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FreshApplicationTests {

	@Autowired
	private SearchProductRepository searchProductRepository;

	@Test
	public void contextLoads() {
//		Long x = new Long(1);
//		SearchProduct searchProduct = new SearchProduct(x,1,"1",1.1,"1",x);
//		searchProductRepository.save(searchProduct);
	}

//	public void deleteIndex() {
//		elasticsearchOperations.indexOps(SearchProduct.class).delete();
//	}
//
//	public void buildIndex() {
//		elasticsearchOperations.indexOps(SearchProduct.class).refresh();
//		searchProductRepository.deleteAll();
//		searchProductRepository.saveAll(ConvertProduct(productRepository.findALLSearchProducts()));
//	}
//
//	private List<SearchProduct> ConvertProduct(List<Object> objects){
//		List<SearchProduct> searchProducts = new ArrayList<>();
//		for (Object obj : objects)
//			searchProducts.add((SearchProduct) obj);
//		return searchProducts;
//	}

}
