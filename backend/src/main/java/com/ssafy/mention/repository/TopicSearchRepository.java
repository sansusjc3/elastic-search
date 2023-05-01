package com.ssafy.mention.repository;

import com.ssafy.mention.domain.TopicDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface TopicSearchRepository extends ElasticsearchRepository<TopicDocument, Long> {
    List<TopicDocument> findByTitle(String title);
}
