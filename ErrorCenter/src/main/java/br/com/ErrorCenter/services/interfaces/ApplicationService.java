package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.entities.ApplicationEntity;

import java.util.Optional;
import java.util.Set;

public interface ApplicationService {

    Set<ApplicationEntity> findAll();

    Optional<ApplicationEntity> findById(Long applicationId);

}
