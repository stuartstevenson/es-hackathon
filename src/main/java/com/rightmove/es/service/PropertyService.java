package com.rightmove.es.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rightmove.es.dao.PropertyDao;
import com.rightmove.es.domain.Property;

@Component
public class PropertyService {

	@Autowired
	private PropertyDao propertyDao;
	
	public Collection<Property> listAll() {
		return propertyDao.listAll();
	}

}
