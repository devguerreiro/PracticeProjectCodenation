package br.com.ErrorCenter.services.impl;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.exceptions.event.EmailAlreadyUsedException;
import br.com.ErrorCenter.mappers.ApplicationMapper;
import br.com.ErrorCenter.repositories.ApplicationRepository;
import br.com.ErrorCenter.services.interfaces.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ApplicationDTO save(ApplicationEntity applicationEntity) {
        if (existsByEmail(applicationEntity.getEmail())) {
            throw new EmailAlreadyUsedException();
        }

        applicationEntity.setPassword(
                passwordEncoder.encode(applicationEntity.getPassword())
        );
        applicationEntity.setCreatedAt(OffsetDateTime.now());
        return applicationMapper.map(applicationRepository.save(applicationEntity));
    }

    public boolean existsByEmail(String email) {
        return applicationRepository.findByEmail(email).isPresent();
    }

}
