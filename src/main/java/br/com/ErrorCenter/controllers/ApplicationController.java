package br.com.ErrorCenter.controllers;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.services.impl.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/application")
@RestController
public class ApplicationController {

    @Autowired
    private ApplicationServiceImpl applicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationDTO create(@Valid @RequestBody ApplicationEntity applicationEntity) {
        return applicationService.save(applicationEntity);
    }

}
