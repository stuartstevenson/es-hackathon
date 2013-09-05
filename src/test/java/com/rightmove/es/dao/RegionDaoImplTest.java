package com.rightmove.es.dao;

import com.rightmove.es.dao.impl.RegionDaoImpl;
import com.rightmove.es.domain.Region;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

public class RegionDaoImplTest {

    @Test
	@Ignore
    public void listAll() {
        Collection<Region> regions = new RegionDaoImpl().listAll();
        for (Region region : regions) {
            System.out.println(region.toString());
        }
    }
}
