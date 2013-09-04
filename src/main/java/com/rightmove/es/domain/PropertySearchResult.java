package com.rightmove.es.domain;

import org.springframework.data.elasticsearch.core.FacetedPage;

public class PropertySearchResult {
	
    private final String searchPhrase;
    private long tookInMillis;
    private final FacetedPage<Property> properties;
    private PropertyQueryParams queryParamsUsed;

    public PropertySearchResult(String searchPhrase,
                        long tookInMillis,
                        FacetedPage<Property> properties,
                        PropertyQueryParams queryParamsUsed) {
        this.searchPhrase = searchPhrase;
        this.tookInMillis = tookInMillis;
        this.properties = properties;
        this.queryParamsUsed = queryParamsUsed;
    }

    public long getTookInMillis() {
        return tookInMillis;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

	public FacetedPage<Property> getProperties() {
		return properties;
	}

	public PropertyQueryParams getQueryParamsUsed() {
		return queryParamsUsed;
	}

	public void setQueryParamsUsed(PropertyQueryParams queryParamsUsed) {
		this.queryParamsUsed = queryParamsUsed;
	}

}
