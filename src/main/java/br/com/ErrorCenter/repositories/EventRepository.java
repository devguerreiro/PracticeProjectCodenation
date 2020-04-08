package br.com.ErrorCenter.repositories;

import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findByLevel(LevelEnum level);

    List<EventEntity> findByDescriptionContaining(String description);

    List<EventEntity> findByLogContaining(String log);

    List<EventEntity> findByApplicationId(Long applicationId);

    List<EventEntity> findByCreatedAt(LocalDateTime localDateTime);

    List<EventEntity> findByQuantity(Integer quantity);

}
