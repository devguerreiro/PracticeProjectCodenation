package br.com.ErrorCenter.controllers;

import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.enums.LevelEnum;
import br.com.ErrorCenter.services.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/event")
@RestController
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    @PostMapping
    public ResponseEntity<EventDetailDTO> save(@RequestBody EventCreateDTO eventCreateDTO) {
        return new ResponseEntity<>(
                eventService.save(eventCreateDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Page<EventListDTO>> findAll(
            Pageable pageable)
    {
        return new ResponseEntity<>(
                eventService.findAll(pageable),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailDTO> findById(
            Long id)
    {
        return new ResponseEntity<>(
                eventService.findById(id),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "level")
    public ResponseEntity<Page<EventListDTO>> findByLevel(
            @RequestParam LevelEnum level,
            Pageable pageable)
    {
        return new ResponseEntity<>(
                eventService.findAllByLevel(level, pageable),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "description")
    public ResponseEntity<Page<EventListDTO>> findByDescription(
            @RequestParam String description,
            Pageable pageable)
    {
        return new ResponseEntity<>(
                eventService.findAllByDescription(description, pageable),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "log")
    public ResponseEntity<Page<EventListDTO>> findByLog(
            @RequestParam String log,
            Pageable pageable)
    {
        return new ResponseEntity<>(
                eventService.findAllByLog(log, pageable),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "origin_id")
    public ResponseEntity<Page<EventListDTO>> findByOrigin(
            @RequestParam Long origin_id,
            Pageable pageable)
    {
        return new ResponseEntity<>(
                eventService.findAllByApplication(origin_id, pageable),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "datetime")
    public ResponseEntity<Page<EventListDTO>> findByDate(
            @RequestParam LocalDateTime datetime,
            Pageable pageable)
    {
        return new ResponseEntity<>(
                eventService.findAllByDate(datetime, pageable),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "quantity")
    public ResponseEntity<Page<EventListDTO>> findByQuantity(
            @RequestParam Integer quantity,
            Pageable pageable)
    {
        return new ResponseEntity<>(
                eventService.findAllByQuantity(quantity, pageable),
                HttpStatus.OK
        );
    }

}
