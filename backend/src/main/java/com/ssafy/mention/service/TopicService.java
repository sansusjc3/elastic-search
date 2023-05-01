package com.ssafy.mention.service;

import com.ssafy.mention.domain.Topic;
import com.ssafy.mention.repository.TopicRepository;
import com.ssafy.mention.repository.TopicSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicSearchRepository topicSearchRepository;

    @Transactional
    public void saveAllTopic
    }

}
