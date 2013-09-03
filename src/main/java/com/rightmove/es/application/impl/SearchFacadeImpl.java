package com.rightmove.es.application.impl;

import com.rightmove.es.application.SearchFacade;
import com.rightmove.es.application.SearchResult;
import org.springframework.stereotype.Service;

@Service
public class SearchFacadeImpl implements SearchFacade {
    @Override
    public SearchResult getSearchResult(String searchPhrase) {
        return new SearchResult(searchPhrase);
    }
}
