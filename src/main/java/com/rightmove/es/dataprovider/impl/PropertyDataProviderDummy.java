package com.rightmove.es.dataprovider.impl;

import com.rightmove.es.dao.PropertyDao;
import com.rightmove.es.dataprovider.PropertyDataProvider;
import com.rightmove.es.domain.Property;
import com.rightmove.es.utils.StretchyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PropertyDataProviderDummy implements PropertyDataProvider {

	@Autowired
	private PropertyDao propertyDao;
	
	@Override
	public Collection<Property> listAll() {
		return propertyDao.listAllByFile(StretchyUtils.getAllJSONFiles()[0]);
	}

}
