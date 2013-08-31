package com.rightmove.es.repositories;

import com.rightmove.es.domain.Property;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface PropertyRepository extends ElasticsearchCrudRepository<Property, Long> {
}
