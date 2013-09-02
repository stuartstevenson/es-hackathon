package com.rightmove.es.service;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rightmove.es.domain.Property;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class PropertyServiceTest {

	@Autowired
	private PropertyService propertyService;

	@Test
	public void indexProperty() {
		Property property = new Property();
		property.setId(1L);
		property.setIncode("INC");
		property.setOutcode("OUT");
		property.setSummary("summary test");
		propertyService.indexProperty(property);
	}

	@Test
	public void indexManyProperties() throws Exception {
		ArrayList<Property> properties = new ArrayList<Property>();
		for (long i = 1; i < 10L; i++) {
			Property property = new Property();
			property.setId(i);
			property.setIncode("AB" + i % 10);
			property.setOutcode(i % 10 + "CD");
			property.setSummary("summary test " + i);
			properties.add(property);
		}
		propertyService.indexProperties(properties);
	}
}
