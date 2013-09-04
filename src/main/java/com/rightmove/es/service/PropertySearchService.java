package com.rightmove.es.service;

import org.springframework.data.elasticsearch.core.FacetedPage;

import com.rightmove.es.domain.Property;
import com.rightmove.es.domain.PropertyFilter;

public interface PropertySearchService {
    
	FacetedPage<Property> search(String searchPhrase);

	FacetedPage<Property> search(String searchPhrase, PropertyFilter propertyFilter);
	
}
