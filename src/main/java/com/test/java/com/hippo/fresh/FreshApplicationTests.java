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
	public void contextLoads() {}

}
