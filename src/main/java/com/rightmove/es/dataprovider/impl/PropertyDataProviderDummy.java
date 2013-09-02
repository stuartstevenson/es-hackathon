package com.rightmove.es.dataprovider.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rightmove.es.dao.PropertyDao;
import com.rightmove.es.dataprovider.PropertyDataProvider;
import com.rightmove.es.domain.Property;

@Component
public class PropertyDataProviderDummy implements PropertyDataProvider {

	@Autowired
	private PropertyDao propertyDao;
	
	@Override
	public Collection<Property> listAll() {
		return propertyDao.listAll();
	}

}
