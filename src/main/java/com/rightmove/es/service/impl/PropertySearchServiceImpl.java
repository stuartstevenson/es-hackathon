package com.rightmove.es.service.impl;

import com.rightmove.es.service.PropertySearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.facet.FacetBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertySearchServiceImpl implements PropertySearchService {

    @Autowired
    private Client client;

    @Override
    public SearchResponse getSearchResponse(String searchPhrase) {
        return client.prepareSearch("property-search-index")
                .setTypes("rm-property")
                .setQuery(QueryBuilders.termQuery("summary", searchPhrase))
                .addFacet(FacetBuilders.termsFacet("outcode1").fields("outcode"))
                .execute().actionGet();
    }
}
