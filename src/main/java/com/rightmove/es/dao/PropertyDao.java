package com.rightmove.es.dao;

import com.rightmove.es.domain.Property;

import java.io.File;
import java.util.Collection;

public interface PropertyDao {

	Collection<Property> listAll();

	Collection<Property> listAllByFile(File file);
}
