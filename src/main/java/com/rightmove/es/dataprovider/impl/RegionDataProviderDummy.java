package com.rightmove.es.dataprovider.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rightmove.es.dao.RegionDao;
import com.rightmove.es.dataprovider.RegionDataProvider;
import com.rightmove.es.domain.Region;

@Component
public class RegionDataProviderDummy implements RegionDataProvider {

	@Autowired
	private RegionDao regionDao;
	
	@Override
	public Collection<Region> listAll() {
		return regionDao.listAll();
	}

}
