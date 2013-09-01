package com.rightmove.es.dao;

import com.rightmove.es.domain.Region;
import org.junit.Test;

import java.util.Collection;

public class RegionDaoTest {

    @Test
    public void listAll() {
        Collection<Region> regions = new RegionDao().listAll();
        for (Region region : regions) {
            System.out.println(region.toString());
        }
    }
}
