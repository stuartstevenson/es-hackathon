package com.rightmove.es.dao;

import com.rightmove.es.dao.impl.PropertyDaoImpl;
import com.rightmove.es.domain.Property;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class PropertyDaoImplTest {

	@Test
	public void listAll() throws Exception {
		Collection<Property> properties = new PropertyDaoImpl().listAll();
		System.out.println("Found " + properties.size() + " properties");
		Assert.assertTrue(properties.size() > 0);
	}
}
