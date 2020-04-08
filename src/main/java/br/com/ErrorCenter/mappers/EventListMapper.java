package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventListMapper {

    @Mapping(target = "origin_id", source = "eventEntity.application.id")
    EventListDTO map(EventEntity eventEntity);

    List<EventListDTO> map(List<EventEntity> eventEntities);

}
