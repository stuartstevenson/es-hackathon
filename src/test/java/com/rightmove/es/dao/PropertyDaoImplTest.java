package com.rightmove.es.dao;

import java.util.Collection;

import org.junit.Test;

import com.rightmove.es.dao.impl.PropertyDaoImpl;
import com.rightmove.es.domain.Property;

public class PropertyDaoImplTest {

	@Test
	public void listAll() throws Exception {
		Collection<Property> properties = new PropertyDaoImpl().listAll();
		for (Property property : properties) {
			System.out.println(property);
		}
	}
}
