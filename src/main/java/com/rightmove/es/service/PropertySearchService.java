package com.rightmove.es.service;

import com.rightmove.es.domain.PropertyFilter;
import com.rightmove.es.domain.PropertySearchResult;

public interface PropertySearchService {
    
	PropertySearchResult search(String searchPhrase);

	PropertySearchResult search(String searchPhrase, PropertyFilter propertyFilter);
	
}
