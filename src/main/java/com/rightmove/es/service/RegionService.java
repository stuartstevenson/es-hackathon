package com.rightmove.es.service;

import com.spatial4j.core.shape.Shape;

public interface RegionService {
    void loadAndIndexRegions();

    long listAll();

	long listAllByPoint(double x, double y);

	public long listAllByPolygon(Shape polygon);
}
