package com.rightmove.es.dao;

import java.util.Collection;

import org.junit.Test;

import com.rightmove.es.dao.impl.RegionDaoImpl;
import com.rightmove.es.domain.Region;

public class RegionDaoImplTest {

    @Test
    public void listAll() {
        Collection<Region> regions = new RegionDaoImpl().listAll();
        for (Region region : regions) {
            System.out.println(region.toString());
        }
    }
}
