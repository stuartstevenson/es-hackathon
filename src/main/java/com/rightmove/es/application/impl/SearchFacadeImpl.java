package com.rightmove.es.application.impl;

import com.rightmove.es.application.SearchFacade;
import com.rightmove.es.application.SearchResult;
import com.rightmove.es.domain.Property;
import com.rightmove.es.domain.RightmoveTermEntry;
import com.rightmove.es.domain.RightmoveTermFacet;
import com.rightmove.es.service.FacetService;
import com.rightmove.es.service.PropertySearchService;
import com.rightmove.es.service.PropertyService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

@Service
public class SearchFacadeImpl implements SearchFacade {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertySearchService propertySearchService;
    @Autowired
    private FacetService facetService;

    @Override
    public SearchResult getSearchResult(String searchPhrase) {

        SearchResponse searchResponse = propertySearchService.getSearchResponse(searchPhrase);

        Collection<Property> properties = propertyService.extractProperties(searchResponse.getHits());

        Collection<RightmoveTermFacet> facets = facetService.extractFacets(searchResponse.getFacets());

        return new SearchResult(searchPhrase, searchResponse.getTookInMillis(), properties, facets);
    }
}
