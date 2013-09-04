package com.rightmove.es.service.impl;

import java.util.Collection;

import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermFilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.facet.request.TermFacetRequestBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import com.rightmove.es.domain.Property;
import com.rightmove.es.domain.PropertyFilter;
import com.rightmove.es.domain.PropertySearchResult;
import com.rightmove.es.service.PropertySearchService;

@Component
public class PropertySearchServiceImpl implements PropertySearchService {

	@Autowired
	private Client client;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Override
	public PropertySearchResult search(String searchPhrase) {
		return search(searchPhrase, null);
	}

	@Override
	public PropertySearchResult search(String searchPhrase, PropertyFilter propertyFilter) {
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder(); 
		queryBuilder.withQuery(QueryBuilders.multiMatchQuery(searchPhrase)
				.field("incode", 5.0f)
				.field("outcode", 3.0f)
				.field("city", 2.0f)
				.field("summary", 0.2f)
				.field("description", 0.2f)
				.field("propertyType", 1.5f)
				.field("propertySubType", 2.0f)
				.field("features", 2.0f));


		if(propertyFilter != null) {
			applyFilters(queryBuilder, propertyFilter);
		}

		defineFacets(queryBuilder);

		SearchQuery searchQuery = queryBuilder.build();

		long start = System.nanoTime();
		FacetedPage<Property> results = esTemplate.queryForPage(searchQuery, Property.class);
		long millisSpent = (System.nanoTime() - start) / 1000000;

		return new PropertySearchResult(searchPhrase, millisSpent, results);
	}

	private void defineFacets(NativeSearchQueryBuilder queryBuilder) {
		queryBuilder.withFacet(new TermFacetRequestBuilder("incode").fields("incode").build());
	}

	private NativeSearchQueryBuilder applyFilters(NativeSearchQueryBuilder queryBuilder, PropertyFilter propertyFilter) {
		Collection<String> incodes = propertyFilter.getIncodeFilters();
		TermFilterBuilder[] incodeFilters = new TermFilterBuilder[incodes.size()];
		
		int i = 0;
		for (String incode : propertyFilter.getIncodeFilters()) {
			TermFilterBuilder filter = FilterBuilders.termFilter("incode", incode);
			incodeFilters[i++] = filter;
		}
		return queryBuilder.withFilter(FilterBuilders.orFilter(incodeFilters));
	}
}
