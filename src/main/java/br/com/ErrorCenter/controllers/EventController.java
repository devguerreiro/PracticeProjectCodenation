package br.com.ErrorCenter.controllers;

import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.enums.LevelEnum;
import br.com.ErrorCenter.services.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
            @RequestParam(required = false) Long applicationId,
            @RequestParam(required = false) LocalDateTime date,
            @RequestParam(required = false) Integer quantity,
            Pageable pageable
    )
    {
        return new ResponseEntity<>(
                eventService.findByAny(
                        level,
                        description,
                        log,
                        applicationId,
                        date,
                        quantity,
                        pageable
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailDTO> retrieve(@PathVariable Long id)
    {
        return new ResponseEntity<>(
                eventService.findById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<EventDetailDTO> create(@RequestBody EventCreateDTO eventCreateDTO) {
        return new ResponseEntity<>(
                eventService.save(eventCreateDTO),
                HttpStatus.CREATED
        );
    }

}
