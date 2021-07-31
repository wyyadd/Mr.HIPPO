package com.hippo.fresh.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchProductRepository extends ElasticsearchRepository<SearchProduct, String> {

}
