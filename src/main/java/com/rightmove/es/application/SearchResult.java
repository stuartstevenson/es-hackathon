package com.rightmove.es.application;

import com.rightmove.es.domain.Property;

import java.util.Collection;

public class SearchResult {
    private final String searchPhrase;
    private final Collection<Property> properties;

    public SearchResult(String searchPhrase, Collection<Property> properties) {
        this.searchPhrase = searchPhrase;
        this.properties = properties;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public Collection<Property> getProperties() {
        return properties;
    }
}
