package br.com.ErrorCenter.services.impl;

import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.repositories.ApplicationRepository;
import br.com.ErrorCenter.services.interfaces.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Page<ApplicationEntity> findAll(Pageable pageable) {
        return applicationRepository.findAll(pageable);
    }

    @Override
    public Optional<ApplicationEntity> findById(Long applicationId) {
        return applicationRepository.findById(applicationId);
    }

}
