package br.com.ErrorCenter.controllers;

import br.com.ErrorCenter.dtos.EventDTO;
import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.exceptions.ResourceNotFoundException;
import br.com.ErrorCenter.mappers.EventMapper;
import br.com.ErrorCenter.services.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/event")
@RestController
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private EventMapper eventMapper;

    @GetMapping
    public ResponseEntity<List<EventDTO>> findAll() {
        return new ResponseEntity<>(
                eventMapper.map(eventService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventEntity> findById(@PathVariable Long eventId) {
        return new ResponseEntity<>(
                eventService.findById(eventId)
                        .orElseThrow(() -> new ResourceNotFoundException("event")),
                HttpStatus.OK
        );
    }

}
