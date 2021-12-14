package com.hippo.fresh.search;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.exception.SuggestionNotExistException;
import com.hippo.fresh.utils.ResponseUtils;
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
public class SearchSuggestionService {
    static final String SUGGESTION_INDEX = "suggestion";

    private final ElasticsearchOperations elasticsearchOperations;

    private final SearchProductRepository searchProductRepository;

    @Autowired
    public SearchSuggestionService(final ElasticsearchOperations elasticsearchOperations, final SearchProductRepository searchProductRepository) {
        super();
        this.elasticsearchOperations = elasticsearchOperations;
        this.searchProductRepository = searchProductRepository;
    }

    //得到搜索建议
    public ResponseUtils fetchSuggestions(String query) {
        QueryBuilder queryBuilder = QueryBuilders
                //通配符搜索
                .wildcardQuery("name", query+"*");

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
             //每次发回10个
                .withPageable(PageRequest.of(0, 10))
                .build();

        SearchHits<SearchSuggestion> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        SearchSuggestion.class,
                        IndexCoordinates.of(SUGGESTION_INDEX));

        List<String> suggestions = new ArrayList<String>();

        searchSuggestions.getSearchHits().forEach(searchHit->{
            suggestions.add(searchHit.getContent().getName());
        });

        //如果找不到， 发出异常
        if(suggestions.size() == 0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("search", query);
            throw new SuggestionNotExistException(jsonObject);
        }
        else
            return ResponseUtils.success("查找建议成功", suggestions);
    }
}
