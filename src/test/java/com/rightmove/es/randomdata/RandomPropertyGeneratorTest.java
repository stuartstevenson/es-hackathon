package com.rightmove.es.randomdata;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rightmove.es.domain.Property;
import com.rightmove.es.service.PropertyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class RandomPropertyGeneratorTest {

	@Autowired
	private PropertyService propertyService;

	@Test
	@Ignore("should not be part of the build")
	public void indexRandomProperties() throws Exception {
		for (int i = 0; i < 40000; i++) {
			ArrayList<Property> properties = new ArrayList<Property>();
			for (long j = 1; j < 100; j++) {
				Property property = (Property) new RandomObjectGenerator().fill(Property.class, 2);
				properties.add(property);
			}
			propertyService.indexProperties(properties);
		}
	}
}
