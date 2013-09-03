package com.rightmove.es.service;

import java.util.Collection;

import com.rightmove.es.domain.Property;
import org.elasticsearch.search.SearchHits;

public interface PropertyService {

	void indexProperty(Property property);
	
	void indexProperties(Collection<Property> properties);

    Collection<Property> extractProperties(SearchHits searchPhrase);
}
