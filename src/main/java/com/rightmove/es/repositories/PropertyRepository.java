package com.rightmove.es.repositories;

import com.rightmove.es.domain.Property;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PropertyRepository extends ElasticsearchRepository<Property, Long> {
}
