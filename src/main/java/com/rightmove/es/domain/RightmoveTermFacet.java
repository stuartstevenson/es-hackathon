package com.rightmove.es.domain;

import java.util.Collection;
import java.util.List;

public class RightmoveTermFacet {

    private String name;
    private long totalCount;
    private Collection<RightmoveTermEntry> rightmoveTermEntryList;

    public RightmoveTermFacet(String name,
                              long totalCount,
                              Collection<RightmoveTermEntry> rightmoveTermEntryList) {
        this.name = name;
        this.totalCount = totalCount;


        this.rightmoveTermEntryList = rightmoveTermEntryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public Collection<RightmoveTermEntry> getRightmoveTermEntryList() {
        return rightmoveTermEntryList;
    }

    public void setRightmoveTermEntryList(List<RightmoveTermEntry> rightmoveTermEntryList) {
        this.rightmoveTermEntryList = rightmoveTermEntryList;
    }
}
