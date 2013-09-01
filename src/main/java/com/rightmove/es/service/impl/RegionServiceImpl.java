package com.rightmove.es.service.impl;

import com.rightmove.es.dao.RegionDao;
import com.rightmove.es.domain.Region;
import com.rightmove.es.service.RegionService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;
import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Component
public class RegionServiceImpl implements RegionService {

    private static Logger log = Logger.getLogger(RegionService.class);

    private static Integer id =1;


    @Autowired
    private Client client;
    @Autowired
    private RegionDao regionDao;

    public void loadAndIndexRegions() {
        createIndex();

        for (Region region : regionDao.listAll()) {
            indexRegion(region);
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

    private void indexRegion(Region region) {
        try {

            String json = "{" +
                    "\"name\":\""+region.getName()+"\"," +
                    "\"polygon\": {" +
                    "\"type\":\"polygon\"," +
                    "\"coordinates\":[[" +
                    getCoordinates(region.getPolygon()) +
                    "]]" +
                    "}" +
                    "}";


            client.prepareIndex("region-index", "region", ""+id++).setSource(json).execute().actionGet();

            log.debug("index object: "+region.getName());

        } catch (Exception e) {
            log.debug("failed to index object:"+region.getName());
            // ignore
        }
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
