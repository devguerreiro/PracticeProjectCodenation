package br.com.ErrorCenter.repositories;

import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    Page<EventEntity> findByLevel(LevelEnum level, Pageable pageable);

    Page<EventEntity> findByDescriptionContaining(String description, Pageable pageable);

    Page<EventEntity> findByLogContaining(String log, Pageable pageable);

    Page<EventEntity> findByApplicationId(Long applicationId, Pageable pageable);

    Page<EventEntity> findByCreatedAt(LocalDateTime localDateTime, Pageable pageable);

    Page<EventEntity> findByQuantity(Integer quantity, Pageable pageable);

}
