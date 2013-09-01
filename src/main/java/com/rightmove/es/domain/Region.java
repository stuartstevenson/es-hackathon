package com.rightmove.es.domain;

import com.vividsolutions.jts.geom.Polygon;

public class Region {

    private String name;
    private Polygon polygon;

    public Region() {
    }

    public Region(String name, Polygon polygon) {
        this.name = name;
        this.polygon = polygon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", polygon=" + polygon +
                '}';
    }
}
