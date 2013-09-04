package com.rightmove.es.domain;

import org.springframework.data.elasticsearch.core.FacetedPage;

public class PropertySearchResult {
    private final String searchPhrase;
    private long tookInMillis;
    private final FacetedPage<Property> properties;

    public PropertySearchResult(String searchPhrase,
                        long tookInMillis,
                        FacetedPage<Property> properties) {
        this.searchPhrase = searchPhrase;
        this.tookInMillis = tookInMillis;
        this.properties = properties;
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
}
