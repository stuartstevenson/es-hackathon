package com.rightmove.es.service;

import static org.junit.Assert.assertEquals;

import org.elasticsearch.common.geo.ShapeBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @Test
    @Ignore("this test fails against an empty ES data. It works in the second run")
    // TODO 
    // Caused by: org.elasticsearch.search.SearchParseException: [region-index][0]: query[ConstantScore(*:*)],from[-1],size[-1]: Parse Failure [Failed to parse source [{"query":{"match_all":{}},"filter":{"geo_shape":{"polygon":{"shape":{"type":"Point","coordinates":[75.0,75.0]},"relation":"intersects"},"_name":null}}}]]
    // at org.elasticsearch.search.SearchService.parseSource(SearchService.java:577)
    public void loadAndIndexRegions() throws Exception{
        regionService.loadAndIndexRegions();

        //seems to be slow at indexing all the records. with a pause the match all query eventually catches up
        Thread.sleep(3000);
        
        assertEquals("Expect 10 regions to be indexed", 10l, regionService.listAll());

		assertEquals("Expect 2 regions to be returned", 2l, regionService.listAllByPoint(75,75));

		ShapeBuilder.PolygonBuilder polygonBuilder = ShapeBuilder.newPolygon();

		polygonBuilder.point(25, 25)
				.point(25, 125)
				.point(125, 125)
				.point(125, 25);

		assertEquals("Expect 1 region to be returned", 1l, regionService.listAllByPolygon(polygonBuilder.build()));

	}
}
