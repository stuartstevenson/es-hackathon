package com.rightmove.es.controller;

import com.rightmove.es.application.SearchFacade;
import com.rightmove.es.application.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    @Autowired
    private SearchFacade searchFacade;

    @RequestMapping("/search")
    @ResponseBody
    public SearchResult
    doSearch(SearchForm searchForm) {
        return searchFacade.getSearchResult(searchForm.getSearchPhrase());
    }
}
