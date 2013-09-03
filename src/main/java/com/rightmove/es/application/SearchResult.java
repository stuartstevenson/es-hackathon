package com.rightmove.es.application;

import com.rightmove.es.domain.Property;
import com.rightmove.es.domain.RightmoveTermFacet;

import java.util.Collection;

public class SearchResult {
    private final String searchPhrase;
    private final Collection<Property> properties;
    private final Collection<RightmoveTermFacet> facets;

    public SearchResult(String searchPhrase,
                        Collection<Property> properties,
                        Collection<RightmoveTermFacet> facets) {
        this.searchPhrase = searchPhrase;
        this.properties = properties;
        this.facets = facets;
    }

    public Collection<RightmoveTermFacet> getFacets() {
        return facets;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public Collection<Property> getProperties() {
        return properties;
    }
}
