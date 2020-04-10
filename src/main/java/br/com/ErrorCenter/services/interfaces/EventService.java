package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.enums.LevelEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface EventService {

    Page<EventListDTO> findAllByAnyParam(
            LevelEnum level,
            String description,
            String log,
            Long application_id,
            LocalDateTime created_at,
            Integer quantity,
            Pageable pageable
    );

    Page<EventListDTO> findAll(Pageable pageable);

    Page<EventListDTO> findAllByLevel(LevelEnum levelEnum, Pageable pageable);

    Page<EventListDTO> findAllByDescription(String description, Pageable pageable);

    Page<EventListDTO> findAllByLog(String log, Pageable pageable);

    Page<EventListDTO> findAllByApplication(Long id, Pageable pageable);

    Page<EventListDTO> findAllByDate(LocalDateTime localDateTime, Pageable pageable);

    Page<EventListDTO> findAllByQuantity(Integer quantity, Pageable pageable);

    EventDetailDTO findById(Long eventId);

    EventDetailDTO save(EventCreateDTO eventCreateDTO);

}
