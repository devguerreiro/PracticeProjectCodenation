package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.enums.LevelEnum;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    List<EventListDTO> findAllByAnyParam(
            LevelEnum level,
            String description,
            String log,
            Long application_id,
            LocalDateTime created_at,
            Integer quantity
    );

    List<EventListDTO> findAll();

    List<EventListDTO> findAllByLevel(LevelEnum levelEnum);

    List<EventListDTO> findAllByDescription(String description);

    List<EventListDTO> findAllByLog(String log);

    List<EventListDTO> findAllByApplication(Long id);

    List<EventListDTO> findAllByDate(LocalDateTime localDateTime);

    List<EventListDTO> findAllByQuantity(Integer quantity);

    EventDetailDTO findById(Long eventId);

    EventDetailDTO save(EventCreateDTO eventCreateDTO);

}
