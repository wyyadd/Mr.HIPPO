package com.hippo.fresh.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchSuggestionRepository extends ElasticsearchRepository<SearchSuggestion, String> {
}
