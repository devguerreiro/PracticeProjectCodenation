package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.entities.ApplicationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ApplicationService {

    Page<ApplicationEntity> findAll(Pageable pageable);

    Optional<ApplicationEntity> findById(Long applicationId);

}
