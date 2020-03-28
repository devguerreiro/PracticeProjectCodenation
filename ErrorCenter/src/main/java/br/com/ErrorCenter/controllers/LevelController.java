package br.com.ErrorCenter.controllers;

import br.com.ErrorCenter.dtos.LevelDTO;
import br.com.ErrorCenter.entities.LevelEntity;
import br.com.ErrorCenter.mappers.LevelMapper;
import br.com.ErrorCenter.services.impl.LevelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/level")
@RestController
public class LevelController {

    @Autowired
    private LevelServiceImpl levelService;

    @Autowired
    private LevelMapper levelMapper;

    @PostMapping
    public ResponseEntity<LevelEntity> create(@RequestBody LevelEntity levelEntity) {
        return new ResponseEntity<>(
                    levelService.save(levelEntity),
        HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<LevelDTO>> findAll() {
        return new ResponseEntity<>(
                levelMapper.mapToDto(levelService.findAll()),
                HttpStatus.OK);
    }

}
