package br.com.ErrorCenter.controllers;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.services.impl.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/application")
@RestController
public class ApplicationController {

    @Autowired
    private ApplicationServiceImpl applicationService;

    @PostMapping
    public ResponseEntity<ApplicationDTO> create(@RequestBody ApplicationEntity applicationEntity) {
        return new ResponseEntity<>(
                applicationService.save(applicationEntity),
                HttpStatus.CREATED
        );
    }

}
