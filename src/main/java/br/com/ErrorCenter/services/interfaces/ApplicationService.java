package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;

public interface ApplicationService {

    ApplicationDTO save(ApplicationEntity applicationEntity);

}
