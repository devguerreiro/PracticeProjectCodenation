package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EventCreateMapper {

    @Mapping(target = "origin_id", source = "eventEntity.application.id")
    EventCreateDTO map(EventEntity eventEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "application", source = "applicationEntity"),
    })
    EventEntity map(EventCreateDTO eventCreateDTO, ApplicationEntity applicationEntity);

}
