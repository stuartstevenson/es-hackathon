package com.rightmove.es.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PropertyFilter {
	
	// Map field name -> list of values
	private Map<String, Collection<String>> filters;

	public PropertyFilter() {
		filters = new HashMap<String, Collection<String>>();
	}


	public void addFilter(String incode, String fieldName) {
		incode = incode.toLowerCase(); // ES
		Collection<String> incodeFilters = filters.get(fieldName);
		if(incodeFilters == null) {
			incodeFilters = new ArrayList<String>();
			filters.put(fieldName, incodeFilters);
		}
		incodeFilters.add(incode);

	}

	public Collection<String> getFilters(String fieldName) {
		Collection<String> incodeFilters = filters.get(fieldName);
		if(incodeFilters == null) {
			incodeFilters = new ArrayList<String>();
		}
		return incodeFilters;
	}
}
