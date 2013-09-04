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
	
	public void addIncodeFilter(String incode) {
		incode = incode.toLowerCase(); // ES
		Collection<String> incodeFilters = filters.get("incode");
		if(incodeFilters == null) {
			incodeFilters = new ArrayList<String>();
			filters.put("incode", incodeFilters);
		}
		incodeFilters.add(incode);
		
	}
	
	public Collection<String> getIncodeFilters() {
		Collection<String> incodeFilters = filters.get("incode");
		if(incodeFilters == null) {
			incodeFilters = new ArrayList<String>();
		}
		return incodeFilters;
	}
}
