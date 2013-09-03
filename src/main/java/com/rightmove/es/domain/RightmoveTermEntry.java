package com.rightmove.es.domain;

public class RightmoveTermEntry {
    private String term;
    private int count;

    public RightmoveTermEntry(String term, int count) {
        this.term = term;
        this.count = count;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
