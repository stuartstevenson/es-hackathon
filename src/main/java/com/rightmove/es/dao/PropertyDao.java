package com.rightmove.es.dao;

import java.util.Collection;
import com.rightmove.es.domain.Property;

public interface PropertyDao {

	Collection<Property> listAll();
	
}
