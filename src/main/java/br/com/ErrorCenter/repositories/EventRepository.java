package br.com.ErrorCenter.repositories;

import br.com.ErrorCenter.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findByLevel(String level);

    List<EventEntity> findByDescription(String description);

    List<EventEntity> findByLogId(Long logId);

    List<EventEntity> findByApplicationId(Long applicationId);

    List<EventEntity> findByCreatedAt(LocalDateTime localDateTime);

    List<EventEntity> findByQuantity(int quantity);

}
