package com.rightmove.es.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;

import com.rightmove.es.domain.Property;
import com.rightmove.es.repositories.PropertyRepository;
import com.rightmove.es.service.PropertyService;

@Component
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public void indexProperty(Property property) {
		propertyRepository.index(property);
	}

	@Override
	public void indexProperties(Collection<Property> properties) {
		List<IndexQuery> indexQueries = new ArrayList<IndexQuery>();

		for (Property property : properties) {
			IndexQuery indexQuery = new IndexQuery();
			indexQuery.setId(Long.toString(property.getId()));
			indexQuery.setObject(property);
			indexQueries.add(indexQuery);
		}

		elasticsearchTemplate.bulkIndex(indexQueries);
	}

}
