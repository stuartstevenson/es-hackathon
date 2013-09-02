package com.rightmove.es.service.impl;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.geo.ShapeRelation;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rightmove.es.dataprovider.RegionDataProvider;
import com.rightmove.es.domain.Region;
import com.rightmove.es.service.RegionService;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.shape.Shape;
import com.spatial4j.core.shape.impl.PointImpl;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

@Component
public class RegionServiceImpl implements RegionService {

    private static Integer id =1;

    @Autowired
    private Client client;
    @Autowired
    private RegionDataProvider regionDataProvider;

    @Override
    public void loadAndIndexRegions() {

	    BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

		createIndex();

	    for(Region region : regionDataProvider.listAll()) {
		    bulkRequestBuilder.add(new IndexRequest("region-index", "region",
				    String.valueOf(id++)).source(indexRegion(region)));
	    }

	    if(bulkRequestBuilder.execute().actionGet().hasFailures()) {
		    throw new RuntimeException();
	    }
    }

    @Override
    public long listAll() {
        SearchResponse searchResponse = client.prepareSearch("region-index")
                .setTypes("region")
                .setQuery(QueryBuilders.matchAllQuery())
                .execute().actionGet();
        return searchResponse.getHits().getTotalHits();
    }

	@Override
	public long listAllByPoint(double x, double y) {

		SearchResponse searchResponse = client.prepareSearch("region-index")
				.setTypes("region")
				.setQuery(QueryBuilders.matchAllQuery())
				.setFilter(FilterBuilders.geoShapeFilter("polygon", new PointImpl(x, y, SpatialContext.GEO), ShapeRelation.INTERSECTS))
				.execute().actionGet();
		return searchResponse.getHits().getTotalHits();
	}

	@Override
	public long listAllByPolygon(Shape polygon) {

		SearchResponse searchResponse = client.prepareSearch("region-index")
				.setTypes("region")
				.setQuery(QueryBuilders.matchAllQuery())
				.setFilter(FilterBuilders.geoShapeFilter("polygon", polygon, ShapeRelation.WITHIN))
				.execute().actionGet();
		return searchResponse.getHits().getTotalHits();
	}

    private void createIndex() {
        try {

            String mapping = jsonBuilder().startObject().startObject("region").startObject("properties")
                    .startObject("polygon")
                    .field("type", "geo_shape")
                    .field("tree", "quadtree")
                    .field("precision", "1m")
                    .endObject()
                    .endObject().endObject().endObject().string();

            client.admin().indices().delete(new DeleteIndexRequest("region-index")).actionGet().isAcknowledged();

            client.admin().indices().create(new CreateIndexRequest("region-index")).actionGet().isAcknowledged();

            client.admin().indices().preparePutMapping("region-index").setType("region").setSource(mapping).execute().actionGet().isAcknowledged();

        } catch (Exception e) {
            // ignore
        }
    }

    private String indexRegion(Region region) {

           return "{" +
                    "\"name\":\""+region.getName()+"\"," +
                    "\"polygon\": {" +
                    "\"type\":\"polygon\"," +
                    "\"coordinates\":[[" +
                    getCoordinates(region.getPolygon()) +
                    "]]" +
                    "}" +
                    "}";
    }

    private String getCoordinates(Polygon polygon) {
        StringBuilder stringBuilder = new StringBuilder();

        Coordinate[] coordinates = polygon.getCoordinates();

        for (Coordinate coordinate : coordinates) {
            stringBuilder.append("[").append(coordinate.x).append(",").append(coordinate.y).append("]").append(",");
        }

        //have to finish with the first element
        Coordinate coordinate = coordinates[0];
        stringBuilder.append("[").append(coordinate.x).append(",").append(coordinate.y).append("]");

        return stringBuilder.toString();
    }
}
