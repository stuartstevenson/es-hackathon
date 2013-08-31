package com.rightmove.es.repositories;

import com.rightmove.es.domain.DummyCSVRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface DummyCSVRecordRepository extends ElasticsearchCrudRepository<DummyCSVRecord, String> {
}
