package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.ApplicationListDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationListMapper {

    ApplicationListDTO map(ApplicationEntity applicationEntity);

    List<ApplicationListDTO> map(List<ApplicationEntity> applicationEntities);

}
