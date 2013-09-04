package com.rightmove.es.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rightmove.es.domain.PropertySearchResult;
import com.rightmove.es.service.PropertySearchService;

@Controller
public class SearchController {

    @Autowired
    private PropertySearchService propertySearchService;

    @RequestMapping("/search")
    @ResponseBody
    public PropertySearchResult doSearch(@RequestParam String searchPhrase) {
        return propertySearchService.search(searchPhrase);
    }
}
