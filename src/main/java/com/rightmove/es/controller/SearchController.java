package com.rightmove.es.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rightmove.es.domain.PropertyQueryParams;
import com.rightmove.es.domain.PropertySearchResult;
import com.rightmove.es.service.PropertySearchService;

@Controller
public class SearchController {

    @Autowired
    private PropertySearchService propertySearchService;

    @RequestMapping("/search")
    @ResponseBody
    public PropertySearchResult doSearch(
    		@RequestParam String searchPhrase, 
    		@RequestParam(required = false) Collection<String> incode,
    		@RequestParam(required = false) Collection<String> outcode,
    		@RequestParam(required = false) Collection<String> city,
    		@RequestParam(required = false) Collection<String> propertyType,
    		@RequestParam(required = false) Collection<String> propertySubType,
    		@RequestParam(required = false) String fieldOrderBy,
    		@RequestParam(required = false) String directionOrderBy) {
        
    	PropertyQueryParams propertyQueryParams = new PropertyQueryParams();
    	propertyQueryParams.addFilters("incode", incode);
    	propertyQueryParams.addFilters("outcode", outcode);
    	propertyQueryParams.addFilters("city", outcode);
    	propertyQueryParams.addFilters("propertyType", outcode);
    	propertyQueryParams.addFilters("propertySubType", outcode);
    	propertyQueryParams.setFieldOrderBy(fieldOrderBy);
    	propertyQueryParams.setDirectionOrderBy(directionOrderBy);
		return propertySearchService.search(searchPhrase, propertyQueryParams);
    }
}
