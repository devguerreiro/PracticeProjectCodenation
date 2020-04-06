package br.com.ErrorCenter.services.impl;

import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.repositories.EventRepository;
import br.com.ErrorCenter.services.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<EventEntity> findById(Long eventId) {
        return eventRepository.findById(eventId);
    }

}
