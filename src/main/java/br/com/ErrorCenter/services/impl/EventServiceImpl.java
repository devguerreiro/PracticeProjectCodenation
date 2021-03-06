package br.com.ErrorCenter.services.impl;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import br.com.ErrorCenter.exceptions.ResourceNotFoundException;
import br.com.ErrorCenter.mappers.ApplicationMapper;
import br.com.ErrorCenter.mappers.EventCreateMapper;
import br.com.ErrorCenter.mappers.EventDetailMapper;
import br.com.ErrorCenter.mappers.EventListMapper;
import br.com.ErrorCenter.repositories.ApplicationRepository;
import br.com.ErrorCenter.repositories.EventRepository;
import br.com.ErrorCenter.services.interfaces.EventService;
import br.com.ErrorCenter.utils.VerificationOfId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

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
    private ApplicationMapper applicationMapper;

    @Override
    public Page<EventListDTO> findByAny(LevelEnum level,
                                        String description,
                                        String log,
                                        Long applicationId,
                                        OffsetDateTime createdAt,
                                        Integer quantity,
                                        Pageable pageable)
    {
        if (applicationId != null) {
            VerificationOfId.isValidId(applicationId);
        }

        return eventRepository.findByAny(
                level,
                description,
                log,
                applicationId,
                createdAt,
                quantity,
                pageable
        )
                .map(eventEntity -> eventListMapper.map(eventEntity));
    }

    @Override
    public EventDetailDTO findById(Long eventId) {
        VerificationOfId.isValidId(eventId);

        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        new ResourceNotFoundException("event").getMessage()
                ));

        ApplicationDTO applicationDTO = applicationMapper.map(eventEntity.getApplication());

        return eventDetailMapper.map(eventEntity, applicationDTO);
    }

    @Override
    public EventDetailDTO save(EventCreateDTO eventCreateDTO, String userEmail) {
        ApplicationEntity applicationEntity = applicationRepository.findByEmail(userEmail).get(); //sempre haverá, pois é o email do usuario logado

        EventEntity eventEntity = eventCreateMapper.map(eventCreateDTO, applicationEntity);
        eventEntity.setCreatedAt(OffsetDateTime.now());

        eventRepository.save(eventEntity);

        ApplicationDTO applicationDTO = applicationMapper.map(applicationEntity);

        return eventDetailMapper.map(eventEntity, applicationDTO);
    }

}
