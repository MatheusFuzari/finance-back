package com.fuzari.finance.services;

import com.fuzari.finance.domain.Topic;
import com.fuzari.finance.repository.TopicRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TopicService {

  private final TopicRepository repository;

  public List<Topic> getAllTopics() {
    return repository.findAll();
  }

  public List<Topic> getAllCustomTopics() {
    var user = SecurityContextHolder.getContext();
    System.out.println(user.getAuthentication().getDetails());
    return repository.findAll();
  }

  public Topic createNewTopic(Topic topic_to_create) {
    return repository.save(topic_to_create);
  }

  public void deleteTopic(UUID topic_id) {
    repository.deleteById(topic_id);
  }
}
