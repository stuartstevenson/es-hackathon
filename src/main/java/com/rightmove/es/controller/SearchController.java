package com.rightmove.es.controller;

import com.rightmove.es.application.SearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    @RequestMapping("/search")
    @ResponseBody
    public SearchResult
    doSearch(SearchForm searchForm) {
        return new SearchResult();
    }
}
