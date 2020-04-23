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

    @GetMapping
    public ResponseEntity<Page<EventListDTO>> list(
            @RequestParam(required = false) LevelEnum level,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String log,
            @RequestParam(required = false) Long application_id,
            @RequestParam(required = false) LocalDateTime date,
            @RequestParam(required = false) Integer quantity,
            Pageable pageable
    )
    {
        return ResponseEntity.ok(
                eventService.findByAny(level, description, log, application_id, date, quantity, pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailDTO> retrieve(@PathVariable Long id)
    {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDetailDTO create(@RequestBody EventCreateDTO eventCreateDTO) {
        return eventService.save(eventCreateDTO);
    }

}
