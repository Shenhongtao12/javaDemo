package com.example.es.repository;

import com.example.es.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
}
