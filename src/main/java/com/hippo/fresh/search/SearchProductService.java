package com.hippo.fresh.search;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.exception.ProductNotExistException;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
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
    public List<SearchProduct> processSearch(int page, int pageNum, String productName, int type, int sort, int order, int upperBound, int lowerBound) {
        // 1. Create query on multiple fields enabling fuzzy search
        BoolQueryBuilder boolQueryBuilder =
                QueryBuilders.boolQuery()
//                        .must(QueryBuilders.matchPhrasePrefixQuery(productName, "name"))
                          .must(QueryBuilders.matchPhraseQuery("name",productName))
                          .must(QueryBuilders.multiMatchQuery(productName, "name","detail").fuzziness(Fuzziness.AUTO))
//                        .must(QueryBuilders.functionScoreQuery(QueryBuilders.matchPhraseQuery("name",productName),ScoreFunctionBuilders.weightFactorFunction(300)))
//                        .must(QueryBuilders.functionScoreQuery(QueryBuilders.matchPhraseQuery("detail",productName),ScoreFunctionBuilders.weightFactorFunction(10)))
//                        .must(QueryBuilders.functionScoreQuery(QueryBuilders.matchPhraseQuery("category_id",type),ScoreFunctionBuilders.weightFactorFunction(3000)))
//                        .scoreMode(FunctionScoreQuery.ScoreMode.SUM).setMinScore(10))
                        //价格区间匹配
                        .must(QueryBuilders.rangeQuery("price").from(lowerBound).to(upperBound));

        if(type != 0)
            //商品种类匹配
            boolQueryBuilder.must(QueryBuilders.matchQuery("categoryId",type));


        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(boolQueryBuilder)
                //排序方式匹配
                .withSort(SortBuilders.fieldSort(sort == 1 ? "salesAmount" : "price").order(order == 1 ? SortOrder.DESC : SortOrder.ASC))
                //分页匹配
                .withPageable(PageRequest.of(page,pageNum))
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
        if(productMatches.size() == 0)
            throw new ProductNotExistException(null);
        return productMatches;
    }

    //搜索推荐function
    public List<String> fetchSuggestions(String query) {
        QueryBuilder queryBuilder = QueryBuilders
//                .wildcardQuery("name", query+"*");
                //由通配符查询改为前缀查询
//                .prefixQuery()
                .matchPhrasePrefixQuery("name",query);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                //按照销售额降序
                .withSort(SortBuilders.fieldSort("salesAmount").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0, 7))
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
