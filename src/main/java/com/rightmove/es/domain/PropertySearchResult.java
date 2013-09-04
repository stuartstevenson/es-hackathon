package com.rightmove.es.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.elasticsearch.core.FacetedPage;

public class PropertySearchResult {
	
    private final String searchPhrase;
    private long tookInMillis;
    private final FacetedPage<Property> properties;
    private Collection<String> filterOutcodes;
    private Collection<String> filterIncodes;
    private String fieldOrderBy;
    private String directionOrderBy;

    public PropertySearchResult(String searchPhrase,
                        long tookInMillis,
                        FacetedPage<Property> properties) {
        this.searchPhrase = searchPhrase;
        this.tookInMillis = tookInMillis;
        this.properties = properties;
        this.filterIncodes = new ArrayList<String>();
        this.filterOutcodes = new ArrayList<String>();
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

	public String getFieldOrderBy() {
		return fieldOrderBy;
	}

	public void setFieldOrderBy(String fieldOrderBy) {
		this.fieldOrderBy = fieldOrderBy;
	}

	public String getDirectionOrderBy() {
		return directionOrderBy;
	}

	public void setDirectionOrderBy(String directionOrderBy) {
		this.directionOrderBy = directionOrderBy;
	}

	public Collection<String> getFilterOutcodes() {
		return filterOutcodes;
	}

	public void setFilterOutcodes(Collection<String> filterOutcodes) {
		this.filterOutcodes = filterOutcodes;
	}

	public Collection<String> getFilterIncodes() {
		return filterIncodes;
	}

	public void setFilterIncodes(Collection<String> filterIncodes) {
		this.filterIncodes = filterIncodes;
	}
}
