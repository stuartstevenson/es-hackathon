package com.rightmove.es.service;

import com.rightmove.es.domain.Property;
import org.elasticsearch.search.SearchHits;

import java.util.Collection;

public interface PropertyService {

	void indexProperty(Property property);
	
	void indexProperties(Collection<Property> properties);

    Collection<Property> extractProperties(SearchHits searchPhrase);

	void createIndex();

	void indexAllProperties();
}
