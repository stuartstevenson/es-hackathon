package com.rightmove.es.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rightmove.es.domain.Property;
import com.rightmove.es.domain.PropertyFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class PropertySearchServiceTest {

	@Autowired
	private PropertySearchService propertySearchService;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Test
	public void getSearchResultFilteredWithFacets() {
		
		indexProperty(1L, "NW1");
		indexProperty(2L, "NW1");
		indexProperty(3L, "NW2");
		indexProperty(4L, "NW3");
		indexProperty(5L, "NW4");
		
		PropertyFilter propertyFilter = new PropertyFilter();
		propertyFilter.addIncodeFilter("nw1");
		FacetedPage<Property> results = propertySearchService.search("summary test", propertyFilter).getProperties();
		assertEquals(2, results.getNumberOfElements());
		for (Property property : results) {
			System.out.println(property);
		}
		
		System.out.println();
		
		propertyFilter.addIncodeFilter("nw2");
		results = propertySearchService.search("summary test", propertyFilter).getProperties();
		assertEquals(3, results.getNumberOfElements());
		for (Property property : results) {
			System.out.println(property);
		}
		
		assertEquals(1, results.getFacets().size());
	}

	public void indexProperty(Long id, String incode) {
		Property property = new Property();
		property.setId(id);
		property.setIncode(incode);
		property.setOutcode("OUT");
		property.setSummary("summary test");
		propertyService.indexProperty(property);
	}
}
