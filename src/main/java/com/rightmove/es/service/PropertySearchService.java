package com.rightmove.es.service;

import com.rightmove.es.domain.PropertyQueryParams;
import com.rightmove.es.domain.PropertySearchResult;

public interface PropertySearchService {
    
	PropertySearchResult search(String searchPhrase);

	PropertySearchResult search(String searchPhrase, PropertyQueryParams propertyFilter);
	
}
