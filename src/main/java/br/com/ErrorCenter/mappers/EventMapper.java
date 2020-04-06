package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.EventDTO;
import br.com.ErrorCenter.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "origin", source = "eventEntity.application")

    EventDTO map(EventEntity eventEntity);

    List<EventDTO> map(List<EventEntity> eventEntities);

}
