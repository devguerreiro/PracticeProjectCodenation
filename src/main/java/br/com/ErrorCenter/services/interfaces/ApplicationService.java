package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.entities.ApplicationEntity;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {

    List<ApplicationEntity> findAll();

    Optional<ApplicationEntity> findById(Long applicationId);

}
