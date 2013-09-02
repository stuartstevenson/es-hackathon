package com.rightmove.es.dataprovider;

import java.util.Collection;
import com.rightmove.es.domain.Property;

public interface PropertyDataProvider {

	Collection<Property> listAll();

}
