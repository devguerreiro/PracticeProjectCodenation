package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.enums.LevelEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;

public interface EventService {

    Page<EventListDTO> findByAny(
            LevelEnum level,
            String description,
            String log,
            Long applicationId,
            OffsetDateTime createdAt,
            Integer quantity,
            Pageable pageable
    );

    EventDetailDTO findById(Long eventId);

    EventDetailDTO save(EventCreateDTO eventCreateDTO, String userEmail);

}
