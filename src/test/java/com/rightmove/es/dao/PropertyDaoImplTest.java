package com.rightmove.es.dao;

import com.rightmove.es.dao.impl.PropertyDaoImpl;
import com.rightmove.es.domain.Property;
import com.rightmove.es.utils.StretchyUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

public class PropertyDaoImplTest {

	@Test
	public void listAll() throws Exception {
		Collection<Property> properties = new PropertyDaoImpl().listAllByFile(StretchyUtils.getAllJSONFiles()[0]);
		System.out.println("Found " + properties.size() + " properties");
		Assert.assertTrue(properties.size() > 0);
	}

	@Test
	@Ignore
	public void createAllJSONProperties() {
		new PropertyDaoImpl().loadPropertiesFromDBAndSaveToJSON();
		Assert.assertTrue(StretchyUtils.getAllJSONFiles().length > 0);
	}
}
