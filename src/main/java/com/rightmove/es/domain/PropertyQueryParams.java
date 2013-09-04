package com.rightmove.es.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PropertyQueryParams {
	
	// Map field name -> list of values
	private Map<String, Collection<String>> filters;
	private String fieldOrderBy;
    private String directionOrderBy;

	public PropertyQueryParams() {
		filters = new HashMap<String, Collection<String>>();
	}

	public void addFilters(String fieldName, Collection<String> filters) {
		for (String filter : filters) {
			addFilter(fieldName, filter);
		}
	}

	public void addFilter(String fieldName, String filter) {
		filter = filter.toLowerCase(); // ES
		Collection<String> incodeFilters = filters.get(fieldName);
		if(incodeFilters == null) {
			incodeFilters = new ArrayList<String>();
			filters.put(fieldName, incodeFilters);
		}
		incodeFilters.add(filter);
	}

	public Collection<String> getFilters(String fieldName) {
		Collection<String> incodeFilters = filters.get(fieldName);
		if(incodeFilters == null) {
			incodeFilters = new ArrayList<String>();
		}
		return incodeFilters;
	}
	
	public String getFieldOrderBy() {
		return fieldOrderBy;
	}

	public void setFieldOrderBy(String fieldOrderBy) {
		this.fieldOrderBy = fieldOrderBy;
	}

	public String getDirectionOrderBy() {
		return directionOrderBy;
	}

	public void setDirectionOrderBy(String directionOrderBy) {
		this.directionOrderBy = directionOrderBy;
	}
}