package com.rightmove.es.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rightmove.es.dao.PropertyDao;
import com.rightmove.es.domain.Property;
import com.rightmove.es.service.PropertyService;

@Component
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyDao propertyDao;
	
	@Override
	public Collection<Property> listAll() {
		return propertyDao.listAll();
	}

}
