package com.rightmove.es.application;

public class SearchResult {
    private String searchPhrase;

    public SearchResult(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }
}
