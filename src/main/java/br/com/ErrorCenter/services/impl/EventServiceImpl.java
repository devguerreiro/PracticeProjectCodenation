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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public Page<EventListDTO> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(eventEntity -> eventListMapper.map(eventEntity));
    }

    @Override
    public Page<EventListDTO> findAllByLevel(LevelEnum levelEnum, Pageable pageable) {
        return eventRepository.findByLevel(levelEnum, pageable)
                .map(eventEntity -> eventListMapper.map(eventEntity));
    }

    @Override
    public Page<EventListDTO> findAllByDescription(String description, Pageable pageable) {
        return eventRepository.findByDescriptionContaining(description, pageable)
                .map(eventEntity -> eventListMapper.map(eventEntity));
    }

    @Override
    public Page<EventListDTO> findAllByLog(String log, Pageable pageable) {
        return eventRepository.findByLogContaining(log, pageable)
                .map(eventEntity -> eventListMapper.map(eventEntity));
    }

    @Override
    public Page<EventListDTO> findAllByApplication(Long id, Pageable pageable) {
        return eventRepository.findByApplicationId(id, pageable)
                .map(eventEntity -> eventListMapper.map(eventEntity));
    }

    @Override
    public Page<EventListDTO> findAllByDate(LocalDateTime localDateTime, Pageable pageable) {
        return eventRepository.findByCreatedAt(localDateTime, pageable)
                .map(eventEntity -> eventListMapper.map(eventEntity));
    }

    @Override
    public Page<EventListDTO> findAllByQuantity(Integer quantity, Pageable pageable) {
        return eventRepository.findByQuantity(quantity, pageable)
                .map(eventEntity -> eventListMapper.map(eventEntity));
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
        eventRepository.save(eventEntity);
        ApplicationListDTO applicationListDTO = applicationListMapper.map(applicationEntity);

        return eventDetailMapper.map(eventEntity, applicationListDTO);
    }

}
