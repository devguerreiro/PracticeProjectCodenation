package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.ApplicationListDTO;
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
            @Mapping(target = "origin", source = "applicationListDTO")
    })
    EventDetailDTO map(EventEntity eventEntity, ApplicationListDTO applicationListDTO);

}
