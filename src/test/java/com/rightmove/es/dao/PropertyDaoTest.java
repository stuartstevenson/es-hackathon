package com.rightmove.es.dao;

import java.util.Collection;

import org.junit.Test;

import com.rightmove.es.dao.PropertyDao;
import com.rightmove.es.domain.Property;

public class PropertyDaoTest {

	@Test
	public void listAll() throws Exception {
		Collection<Property> properties = new PropertyDao().listAll();
		for (Property property : properties) {
			System.out.println(property);
		}
	}
}
