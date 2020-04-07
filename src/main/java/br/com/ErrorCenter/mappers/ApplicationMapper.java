package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(target = "login", source = "email")
    ApplicationDTO map(ApplicationEntity applicationEntity);

    List<ApplicationDTO> map(List<ApplicationEntity> applicationEntities);

}
