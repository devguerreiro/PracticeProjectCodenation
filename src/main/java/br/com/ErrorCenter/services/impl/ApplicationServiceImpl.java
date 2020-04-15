package br.com.ErrorCenter.services.impl;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.mappers.ApplicationMapper;
import br.com.ErrorCenter.repositories.ApplicationRepository;
import br.com.ErrorCenter.services.interfaces.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public ApplicationDTO save(ApplicationEntity applicationEntity) {
        return applicationMapper.map(applicationRepository.save(applicationEntity));
    }

}
