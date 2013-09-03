package com.rightmove.es.service.impl;

import com.rightmove.es.domain.RightmoveTermEntry;
import com.rightmove.es.domain.RightmoveTermFacet;
import com.rightmove.es.service.FacetService;
import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.Facets;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;


@Component
public class FacetServiceImpl implements FacetService {
    @Override
    public Collection<RightmoveTermFacet> extractFacets(Facets facets) {
        Collection<RightmoveTermFacet> rightmoveTermFacets = new HashSet<>();

        for (Map.Entry<String, Facet> facetEntry : facets.facetsAsMap().entrySet()) {
            TermsFacet termsFacet = (TermsFacet) facetEntry.getValue();
            Collection<RightmoveTermEntry> rightmoveTermEntries = new HashSet<>();
            for (TermsFacet.Entry entry : termsFacet) {
                rightmoveTermEntries.add(new RightmoveTermEntry(entry.getTerm().string(), entry.getCount()));
            }
            rightmoveTermFacets.add(new RightmoveTermFacet(termsFacet.getName(), termsFacet.getTotalCount(), rightmoveTermEntries));
        }

        return rightmoveTermFacets;
    }
}
