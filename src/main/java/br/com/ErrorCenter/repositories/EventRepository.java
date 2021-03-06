package br.com.ErrorCenter.repositories;

import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    @Query("SELECT event FROM EventEntity event WHERE\n" +
            "(:level is null or event.level = :level) AND\n" +
            "(:description is null or event.description like %:description%) AND\n" +
            "(:log is null or event.log like %:log%) AND\n" +
            "(:applicationId is null or event.application.id = :applicationId) AND\n" +
            "(cast(:createdAt as timestamp) is null or event.createdAt = :createdAt) AND\n" +
            "(:quantity is null or event.quantity = :quantity)")
    Page<EventEntity> findByAny(
            @Param("level") LevelEnum level,
            @Param("description") String description,
            @Param("log") String log,
            @Param("applicationId") Long applicationId,
            @Param("createdAt") OffsetDateTime createdAt,
            @Param("quantity") Integer quantity,
            Pageable pageable
    );

}
