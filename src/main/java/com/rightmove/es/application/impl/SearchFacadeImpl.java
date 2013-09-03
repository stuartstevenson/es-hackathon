package com.rightmove.es.application.impl;

import com.rightmove.es.application.SearchFacade;
import com.rightmove.es.application.SearchResult;
import com.rightmove.es.domain.Property;
import com.rightmove.es.domain.RightmoveTermEntry;
import com.rightmove.es.domain.RightmoveTermFacet;
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

    @Override
    public SearchResult getSearchResult(String searchPhrase) {

        SearchResponse searchResponse = propertySearchService.getSearchResponse(searchPhrase);

        Collection<Property> properties = propertyService.extractProperties(searchResponse.getHits());

        Collection<RightmoveTermFacet> facets = new HashSet<>();

        for (Map.Entry<String, Facet> facetEntry : searchResponse.getFacets().facetsAsMap().entrySet()) {
            TermsFacet termsFacet = (TermsFacet) facetEntry.getValue();
            Collection<RightmoveTermEntry> rightmoveTermEntries = new HashSet<>();
            for (TermsFacet.Entry entry : termsFacet) {
                rightmoveTermEntries.add(new RightmoveTermEntry(entry.getTerm().string(), entry.getCount()));
            }
            facets.add(new RightmoveTermFacet(termsFacet.getName(), termsFacet.getTotalCount(), rightmoveTermEntries));
        }

        return new SearchResult(searchPhrase, properties, facets);
    }
}
