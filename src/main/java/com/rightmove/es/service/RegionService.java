package com.rightmove.es.service;

public interface RegionService {
    void loadAndIndexRegions();

    long listAll();

	long listAllByPoint(double x, double y);
}
