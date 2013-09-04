package com.rightmove.es.utils;

import org.elasticsearch.common.geo.GeoPoint;

import java.util.Random;

public class StretchyUtils {

	public static GeoPoint getRandomLocation() {
		long minSize = 0;
		long maxSize = 150;
		Random random = new Random();
		double latitude = (minSize + (maxSize - minSize)) * random.nextDouble();
		double longitude = (minSize + (maxSize - minSize)) * random.nextDouble();
		return new GeoPoint(latitude, longitude);
	}
}
