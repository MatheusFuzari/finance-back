package com.fuzari.finance.repository;


import com.fuzari.finance.domain.Topic;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, UUID> {


  @Query("SELECT t FROM Topic t WHERE t.created_by.id = ?1")
  List<Topic> findAllByUserId(UUID user_id);
}
