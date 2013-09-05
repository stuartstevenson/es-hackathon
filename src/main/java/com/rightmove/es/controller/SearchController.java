package com.rightmove.es.controller;

import com.rightmove.es.domain.PropertyQueryParams;
import com.rightmove.es.domain.PropertySearchResult;
import com.rightmove.es.service.PropertySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

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
    		@RequestParam(required = false) Double priceMin,
    		@RequestParam(required = false) Double priceMax,
    		@RequestParam(required = false) Integer pageSize,
    		@RequestParam(required = false) Integer pageNumber,
    		@RequestParam(required = false) String fieldOrderBy,
    		@RequestParam(required = false) String directionOrderBy) {
        
    	PropertyQueryParams propertyQueryParams = new PropertyQueryParams();
    	propertyQueryParams.addFilters("incode", incode);
    	propertyQueryParams.addFilters("outcode", outcode);
    	propertyQueryParams.addFilters("city", city);
    	propertyQueryParams.addFilters("propertyType", propertyType);
    	propertyQueryParams.addFilters("propertySubType", propertySubType);
    	propertyQueryParams.setPriceMin(priceMin);
    	propertyQueryParams.setPriceMax(priceMax);
    	propertyQueryParams.setPageSize(pageSize);
    	propertyQueryParams.setFieldOrderBy(fieldOrderBy);
    	propertyQueryParams.setPageNumber(pageNumber);
    	propertyQueryParams.setDirectionOrderBy(directionOrderBy);
		return propertySearchService.search(searchPhrase, propertyQueryParams);
    }
}
