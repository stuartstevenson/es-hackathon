package com.rightmove.es.service.impl;

import com.rightmove.es.domain.Property;
import com.rightmove.es.domain.PropertyQueryParams;
import com.rightmove.es.domain.PropertySearchResult;
import com.rightmove.es.service.PropertySearchService;

import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilder;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	public PropertySearchResult search(String searchPhrase, PropertyQueryParams propertyQueryParams) {
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder(); 
		queryBuilder.withQuery(QueryBuilders.multiMatchQuery(searchPhrase)
				.field("incode", 100.0f)
				.field("outcode", 80.0f)
				.field("city", 90.0f)
				.field("summary", 0.3f)
				.field("description", 0.2f)
				.field("propertyType", 1.5f)
				.field("propertySubType", 2.0f)
				.field("features", 1.0f));

		//TODO
		if(propertyQueryParams != null) {
			applyFilters(queryBuilder, propertyQueryParams);
		}

		defineFacets(queryBuilder);

		SearchQuery searchQuery = queryBuilder.build();

		long start = System.nanoTime();
		FacetedPage<Property> results = esTemplate.queryForPage(searchQuery, Property.class);
		long millisSpent = (System.nanoTime() - start) / 1000000;

		return new PropertySearchResult(searchPhrase, millisSpent, results, propertyQueryParams);
	}

	private void defineFacets(NativeSearchQueryBuilder queryBuilder) {
		queryBuilder.withFacet(new TermFacetRequestBuilder("outcode").fields("outcode").build());
		queryBuilder.withFacet(new TermFacetRequestBuilder("incode").fields("incode").build());
		queryBuilder.withFacet(new TermFacetRequestBuilder("city").fields("city").build());
		queryBuilder.withFacet(new TermFacetRequestBuilder("propertyType").fields("propertyType").build());
		queryBuilder.withFacet(new TermFacetRequestBuilder("propertySubType").fields("propertySubType").build());
	}

	// there must be an easier way to do this
	private void applyFilters(NativeSearchQueryBuilder queryBuilder, PropertyQueryParams propertyQueryParams) {
		Collection<FilterBuilder> filterBuilders = new ArrayList<FilterBuilder>(); 

		filterBuilders.addAll(applyFilter(queryBuilder, propertyQueryParams, "incode"));
		filterBuilders.addAll(applyFilter(queryBuilder, propertyQueryParams, "outcode"));
		filterBuilders.addAll(applyFilter(queryBuilder, propertyQueryParams, "city"));
		filterBuilders.addAll(applyFilter(queryBuilder, propertyQueryParams, "propertyType"));
		filterBuilders.addAll(applyFilter(queryBuilder, propertyQueryParams, "propertySubType"));
		// filber also by features?
		
		// this needs refactoring, there must be an easier way to add one filter after the other
		Object[] array = filterBuilders.toArray();
		FilterBuilder[] filterBuilderArray= new FilterBuilder[array.length];
		
		for (int i = 0; i < array.length; i++) {
			FilterBuilder filter = (FilterBuilder) array[i];
			filterBuilderArray[i] = filter;
		}
		
		queryBuilder.withFilter(FilterBuilders.orFilter(filterBuilderArray));
	}

	// this needs refactoring, there must be an easier way to add one filter after the other
	private List<TermFilterBuilder> applyFilter(
			NativeSearchQueryBuilder queryBuilder,
			PropertyQueryParams propertyQueryParams, String fieldName) {
		Collection<String> filterValues = propertyQueryParams.getFilters(fieldName);
		List<TermFilterBuilder> filterBuilders = new ArrayList<TermFilterBuilder>(filterValues.size());

		for (String value : propertyQueryParams.getFilters(fieldName)) {
			TermFilterBuilder filter = FilterBuilders.termFilter(fieldName, value);
			filterBuilders.add(filter);
		}
		return filterBuilders;
	}
}
