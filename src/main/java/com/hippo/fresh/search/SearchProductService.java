package com.hippo.fresh.search;


import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchProductService {
    static final String PRODUCT_INDEX = "productindex";
////    public List<IndexedObjectInformation> createProductIndexBulk(final List<SearchProduct> searchProducts);
////    public String createProductIndex(SearchProduct searchProduct);
//    public void createProductIndexBulk(final List<SearchProduct> products);
////    public void createProductIndex(final SearchProduct product);
////    public void findProductsByName(final String name);
////    public void findProductsByPrice(final Double upperBound, final Double lowerBound);
//    public List<SearchProduct> processSearch(final String query);
//    public List<String> fetchSuggestions(String query);
    private ElasticsearchOperations elasticsearchOperations;

    private SearchProductRepository searchProductRepository;

    @Autowired
    public SearchProductService(final ElasticsearchOperations elasticsearchOperations, final SearchProductRepository searchProductRepository) {
        super();
        this.elasticsearchOperations = elasticsearchOperations;
        this.searchProductRepository = searchProductRepository;
    }

    public void createProductIndexBulk(final List<SearchProduct> products) {
        searchProductRepository.saveAll(products);
    }

    //fuzz搜索功能
    public List<SearchProduct> processSearch(String query) {
        // 1. Create query on multiple fields enabling fuzzy search
        QueryBuilder queryBuilder =
                QueryBuilders
                        .multiMatchQuery(query, "name", "detail")
                        .fuzziness(Fuzziness.AUTO);
//                          .matchPhrasePrefixQuery("name",query);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();

        // 2. Execute search
        SearchHits<SearchProduct> productHits =
                elasticsearchOperations
                        .search(searchQuery, SearchProduct.class,
                                IndexCoordinates.of(PRODUCT_INDEX));

        // 3. Map searchHits to product list
        List<SearchProduct> productMatches = new ArrayList<SearchProduct>();
        productHits.forEach(searchHit->{
            productMatches.add(searchHit.getContent());
        });
        return productMatches;
    }

    //搜索推荐function
    public List<String> fetchSuggestions(String query) {
        QueryBuilder queryBuilder = QueryBuilders
//                .wildcardQuery("name", query+"*");
                //由通配符查询改为前缀查询
                .matchPhrasePrefixQuery("name",query);
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();

        SearchHits<SearchProduct> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        SearchProduct.class,
                        IndexCoordinates.of(PRODUCT_INDEX));

        List<String> suggestions = new ArrayList<String>();

        searchSuggestions.getSearchHits().forEach(searchHit->{
            suggestions.add(searchHit.getContent().getName());
        });
        return suggestions;
    }

}
