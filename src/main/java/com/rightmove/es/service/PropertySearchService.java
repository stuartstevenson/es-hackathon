package com.rightmove.es.service;

import org.elasticsearch.action.search.SearchResponse;

public interface PropertySearchService {
    SearchResponse getSearchResponse(String searchPhrase);
}
