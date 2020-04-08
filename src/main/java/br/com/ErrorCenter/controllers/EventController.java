package br.com.ErrorCenter.controllers;

import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.enums.LevelEnum;
import br.com.ErrorCenter.services.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/params")
    public ResponseEntity<List<EventListDTO>> findAllByAnyParam(
            @RequestParam(required = false) LevelEnum level,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String log,
            @RequestParam(required = false) Long application_id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime created_at,
            @RequestParam(required = false) Integer quantity
    ) {
        return new ResponseEntity<>(
                eventService.findAllByAnyParam(level, description, log, application_id, created_at, quantity),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<EventListDTO>> findAll() {
        return new ResponseEntity<>(
                eventService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(
                eventService.findById(id),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "level")
    public ResponseEntity<List<EventListDTO>> findByLevel(@RequestParam LevelEnum level) {
        return new ResponseEntity<>(
                eventService.findAllByLevel(level),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "description")
    public ResponseEntity<List<EventListDTO>> findByDescription(@RequestParam String description) {
        return new ResponseEntity<>(
                eventService.findAllByDescription(description),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "log")
    public ResponseEntity<List<EventListDTO>> findByLog(@RequestParam String log) {
        return new ResponseEntity<>(
                eventService.findAllByLog(log),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "origin_id")
    public ResponseEntity<List<EventListDTO>> findByOrigin(@RequestParam Long origin_id) {
        return new ResponseEntity<>(
                eventService.findAllByApplication(origin_id),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "datetime")
    public ResponseEntity<List<EventListDTO>> findByDate(
            @RequestParam LocalDateTime datetime)
    {
        return new ResponseEntity<>(
                eventService.findAllByDate(datetime),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "quantity")
    public ResponseEntity<List<EventListDTO>> findByQuantity(@RequestParam Integer quantity) {
        return new ResponseEntity<>(
                eventService.findAllByQuantity(quantity),
                HttpStatus.OK
        );
    }

}
