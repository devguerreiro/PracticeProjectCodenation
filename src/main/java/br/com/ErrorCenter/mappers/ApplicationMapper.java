package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    ApplicationDTO map(ApplicationEntity applicationEntity);

    List<ApplicationDTO> map(List<ApplicationEntity> applicationEntities);

}
