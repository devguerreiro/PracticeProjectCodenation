package br.com.ErrorCenter.services.impl;

import br.com.ErrorCenter.dtos.ApplicationListDTO;
import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import br.com.ErrorCenter.exceptions.ResourceNotFoundException;
import br.com.ErrorCenter.mappers.ApplicationListMapper;
import br.com.ErrorCenter.mappers.EventCreateMapper;
import br.com.ErrorCenter.mappers.EventDetailMapper;
import br.com.ErrorCenter.mappers.EventListMapper;
import br.com.ErrorCenter.repositories.ApplicationRepository;
import br.com.ErrorCenter.repositories.EventRepository;
import br.com.ErrorCenter.services.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private EventListMapper eventListMapper;

    @Autowired
    private EventDetailMapper eventDetailMapper;

    @Autowired
    private EventCreateMapper eventCreateMapper;

    @Autowired
    private ApplicationListMapper applicationListMapper;

    @Override
    public List<EventListDTO> findAllByAnyParam(
            LevelEnum level,
            String description,
            String log,
            Long application_id,
            LocalDateTime created_at,
            Integer quantity
    ) {

        List<EventEntity> events = eventRepository.findAll();
        Stream<EventEntity> stream;

        if (level != null) {
            events = events.parallelStream()
                    .filter(eventEntity -> eventEntity.getLevel().equals(level))
                    .collect(Collectors.toList());
        }

        if (description != null) {
            events = events.parallelStream()
                    .filter(eventEntity -> eventEntity.getDescription().contains(description))
                    .collect(Collectors.toList());
        }

        if (log != null) {
            events = events.parallelStream()
                    .filter(eventEntity -> eventEntity.getLog().contains(log))
                    .collect(Collectors.toList());
        }

        if (application_id != null) {
            events = events.parallelStream()
                    .filter(eventEntity -> eventEntity.getApplication().getId().equals(application_id))
                    .collect(Collectors.toList());
        }

        if (created_at != null) {
            events = events.parallelStream()
                    .filter(eventEntity -> {
                        LocalDateTime dateTime = eventEntity.getCreatedAt();

                        return dateTime != null &&
                                dateTime.truncatedTo(ChronoUnit.SECONDS).equals(created_at);
                    })
                    .collect(Collectors.toList());

        }

        if (quantity != null) {
            events = events.parallelStream()
                    .filter(eventEntity -> eventEntity.getQuantity().equals(quantity))
                    .collect(Collectors.toList());
        }

        return eventListMapper.map(events);
    }

    @Override
    public List<EventListDTO> findAll() {
        return eventListMapper.map(eventRepository.findAll());
    }

    @Override
    public List<EventListDTO> findAllByLevel(LevelEnum levelEnum) {
        return eventListMapper.map(eventRepository.findByLevel(levelEnum));
    }

    @Override
    public List<EventListDTO> findAllByDescription(String description) {
        return eventListMapper.map(eventRepository.findByDescriptionContaining(description));
    }

    @Override
    public List<EventListDTO> findAllByLog(String log) {
        return eventListMapper.map(eventRepository.findByLogContaining(log));
    }

    @Override
    public List<EventListDTO> findAllByApplication(Long id) {
        return eventListMapper.map(eventRepository.findByApplicationId(id));
    }

    @Override
    public List<EventListDTO> findAllByDate(LocalDateTime localDateTime) {
        return eventListMapper.map(eventRepository.findByCreatedAt(localDateTime));
    }

    @Override
    public List<EventListDTO> findAllByQuantity(Integer quantity) {
        return eventListMapper.map(eventRepository.findByQuantity(quantity));
    }

    @Override
    public EventDetailDTO findById(Long eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("evento"));

        ApplicationListDTO applicationListDTO = applicationListMapper.map(eventEntity.getApplication());

        return eventDetailMapper.map(eventEntity, applicationListDTO);
    }

    @Override
    public EventDetailDTO save(EventCreateDTO eventCreateDTO) {
        Long applicationId = eventCreateDTO.getOrigin_id();

        ApplicationEntity applicationEntity = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("application"));

        EventEntity eventEntity = eventCreateMapper.map(eventCreateDTO, applicationEntity);

        eventEntity = eventRepository.save(eventEntity);

        ApplicationListDTO applicationListDTO = applicationListMapper.map(applicationEntity);

        return eventDetailMapper.map(eventEntity, applicationListDTO);
    }

}
