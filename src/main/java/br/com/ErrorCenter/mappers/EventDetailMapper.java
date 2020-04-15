package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EventDetailMapper {

    @Mappings({
            @Mapping(target = "id", source = "eventEntity.id"),
            @Mapping(target = "createdAt", source = "eventEntity.createdAt"),
            @Mapping(target = "origin", source = "applicationDTO")
    })
    EventDetailDTO map(EventEntity eventEntity, ApplicationDTO applicationDTO);

}
