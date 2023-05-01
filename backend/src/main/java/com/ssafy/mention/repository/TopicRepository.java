package com.ssafy.mention.repository;

import com.ssafy.mention.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByEmail(String email);

}
