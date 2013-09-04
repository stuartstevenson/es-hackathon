package com.rightmove.es.service;

import com.rightmove.es.domain.Property;
import com.rightmove.es.domain.PropertyQueryParams;
import com.rightmove.es.utils.StretchyUtils;
import org.elasticsearch.common.geo.GeoPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class PropertySearchServiceTest {

	private static final GeoPoint randomLocation = StretchyUtils.getRandomLocation();

	@Autowired
	private PropertySearchService propertySearchService;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Test
	public void getSearchResultFilteredWithFacets() throws InterruptedException {
		
		indexProperty(1L, "London", "RRR", "AB1", "summary test", null, null);
		indexProperty(2L, "London", "RRR", "AB2", "summary test", null, null);
		indexProperty(3L, "London", "SSS", "AB3", "summary test", null, null);
		indexProperty(4L, "London", "SSS", "AB4", "summary test", null, null);
		indexProperty(5L, "London", "TTT", "AB5", "summary test", null, null);
		
		Thread.sleep(2000L);
		
		PropertyQueryParams propertyQueryParams = new PropertyQueryParams();
		propertyQueryParams.addFilter("outcode", "RRR");
		FacetedPage<Property> results = propertySearchService.search("summary test", propertyQueryParams).getProperties();
//		assertEquals(2, results.getNumberOfElements());
		for (Property property : results) {
			System.out.println(property);
		}

		System.out.println();

		propertyQueryParams.addFilter("outcode", "TTT");
		results = propertySearchService.search("summary test", propertyQueryParams).getProperties();
//		assertEquals(3, results.getNumberOfElements());
		for (Property property : results) {
			System.out.println(property);
		}
		
//		assertTrue(results.getFacets().size() > 0);
	}
	
	@Test
	public void weight() throws InterruptedException {
		
		indexProperty(1L, "Milton Keynes", "AB1", "DE2", "summary test", null, null);
		indexProperty(2L, "Milton Keynes", "AB1", "DE3", "this property has 2 staircases", Arrays.asList("walls"), null);
		indexProperty(3L, "NW1", "NW2");
		indexProperty(4L, "NW1", "NW1");
		indexProperty(5L, "London", "NW3");
		
		Thread.sleep(2000L);
		
		FacetedPage<Property> results = propertySearchService.search("AB1 walls").getProperties();
		//assertEquals(2, results.getNumberOfElements());
		for (Property property : results) {
			System.out.println(property);
		}
	}

	@Test
	public void boost() throws Exception{
		indexProperty(1L, "Milton Keynes", "AB1", "DE2", "summary test", null, 1.0);
		indexProperty(2L, "Milton Keynes", "AB1", "DE3", "this property has 2 staircases", Arrays.asList("walls"), 1.0);
		indexProperty(3L,"London", "AB1", "NW2", "summary test", null, 1.0);
		indexProperty(4L,"London", "AB1", "NW1", "summary test", null, null);
		indexProperty(5L,"London", "AB1", "NW3", "summary test", null, 5.0);

		Thread.sleep(3000);

		FacetedPage<Property> results = propertySearchService.search("AB1 walls").getProperties();

		assertTrue(results.getContent().get(0).getId().equals(5L));

	}
	
	@Test
	public void filters() throws InterruptedException {
		
		indexProperty(1L, "London", "ABC", "DEF", "summary test", null, null);
		indexProperty(2L, "London", "ABC", "GHI", "this property has 2 staircases", null, null);
		indexProperty(3L, "London", "AAA", "BBB", "summary test", null, null);
		
		Thread.sleep(2000L);
		
		PropertyQueryParams propertyFilter = new PropertyQueryParams();
		propertyFilter.addFilters("incode", Arrays.asList("DEF", "GHI"));
		FacetedPage<Property> results = propertySearchService.search("London", propertyFilter).getProperties();
		//assertEquals(2, results.getNumberOfElements());
		for (Property property : results) {
			System.out.println(property);
		}
	}
	
	@Test
	public void order() throws InterruptedException {
		
		indexProperty(1L, "aaaaac", "AAA", "DEF", "summary test", null,null);
		indexProperty(2L, "aaaaaa", "AAA", "GHI", "this property has 2 staircases", null,null);
		indexProperty(3L, "aaaaab", "AAA", "BBB", "summary test", null,null);
		
		Thread.sleep(2000L);
		
		PropertyQueryParams propertyFilter = new PropertyQueryParams();
		propertyFilter.setFieldOrderBy("city");
		propertyFilter.setDirectionOrderBy("DESC");
		FacetedPage<Property> results = propertySearchService.search("AAA", propertyFilter).getProperties();
		//assertEquals(3, results.getNumberOfElements());
		for (Property property : results) {
			System.out.println(property);
		}
	}

	public void indexProperty(Long id, String city, String incode) {
		indexProperty(id, city, "OUT", incode, "summary test", null, null);
	}
	
	public void indexProperty(Long id, String city, String outcode, String incode, String summary, List<String> features, Double boost) {
		Property property = new Property();
		property.setId(id);
		property.setIncode(incode);
		property.setOutcode(outcode);
		property.setCity(city);
		property.setSummary(summary);
		property.setFeatures(features);
		property.setLocation(StretchyUtils.getRandomLocation());
		property.setBoost(boost);
		propertyService.indexProperty(property);
	}
}
