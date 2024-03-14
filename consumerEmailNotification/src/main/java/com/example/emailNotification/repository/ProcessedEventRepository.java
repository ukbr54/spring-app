package com.example.emailNotification.repository;

import com.example.emailNotification.entity.ProcessedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedEventRepository extends JpaRepository<ProcessedEventEntity,Long> {

    ProcessedEventEntity findByMessageId(String messageId);
}
