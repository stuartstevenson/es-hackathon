package com.rightmove.es.service;

import com.rightmove.es.domain.RightmoveTermFacet;
import org.elasticsearch.search.facet.Facets;

import java.util.Collection;

public interface FacetService {
    Collection<RightmoveTermFacet> extractFacets(Facets facets);
}
