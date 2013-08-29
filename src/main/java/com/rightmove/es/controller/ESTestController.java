package com.rightmove.es.controller;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.FilterBuilders.boolFilter;
import static org.elasticsearch.index.query.FilterBuilders.geoBoundingBoxFilter;
import static org.elasticsearch.index.query.FilterBuilders.termFilter;
import static org.elasticsearch.index.query.QueryBuilders.filteredQuery;
import static org.elasticsearch.index.query.QueryBuilders.fuzzyLikeThisQuery;

@Controller
public class ESTestController {

    private Logger log = Logger.getLogger(HomeController.class);

    @Autowired
    private Client client;

    @RequestMapping("/es-test")
    public String showESTestPage(final ModelMap modelMap) {

        indexDummyData();

        modelMap.addAttribute("result",searchDummyData());

        log.debug("estest");
        return "esTest";
    }

    private String searchDummyData() {
        SearchResponse searchResponse = client.prepareSearch("property-index")
                .setTypes("property")
                .setQuery(filteredQuery(fuzzyLikeThisQuery("address", "description").likeText("york"),
                        boolFilter()
                                .must(geoBoundingBoxFilter("point").topLeft(40.73, -74.1).bottomRight(40.71, -73.99))
                                .mustNot(termFilter("prop_type", "house"))
                ))
                .execute().actionGet();

        return searchResponse.toString();
    }

    private void indexDummyData() {
        try {

            String mapping = jsonBuilder().startObject().startObject("property").startObject("properties")
                    .startObject("point")
                    .field("type", "geo_point")
                    .field("lat_lon", true)
                    .endObject()
                    .endObject().endObject().endObject().string();

            client.admin().indices().create(new CreateIndexRequest("property-index")).actionGet().isAcknowledged();

            client.admin().indices().preparePutMapping("property-index").setType("property").setSource(mapping).execute().actionGet().isAcknowledged();

            client.prepareIndex("property-index", "property", "69").setSource(jsonBuilder().startObject()
                    .field("address", "New York")
                    .field("description", "5 bedrooms")
                    .field("prop_type","bungalow")
                    .startObject("point").field("lat", 40.7143528).field("lon", -74.0).endObject()
                    .endObject()).execute().actionGet();

        } catch (Exception e) {
            // ignore
        }
    }


}
