package com.rightmove.es.application.impl;

import com.rightmove.es.application.SearchFacade;
import com.rightmove.es.application.SearchResult;
import com.rightmove.es.domain.Property;
import com.rightmove.es.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SearchFacadeImpl implements SearchFacade {

    @Autowired
    private PropertyService propertyService;

    @Override
    public SearchResult getSearchResult(String searchPhrase) {

        Collection<Property> properties = propertyService.findProperties(searchPhrase);
        return new SearchResult(searchPhrase, properties);
    }
}
