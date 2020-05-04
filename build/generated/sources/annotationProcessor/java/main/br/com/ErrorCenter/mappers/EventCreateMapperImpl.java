package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.entities.EventEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-04T14:40:25-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class EventCreateMapperImpl implements EventCreateMapper {

    @Override
    public EventCreateDTO map(EventEntity eventEntity) {
        if ( eventEntity == null ) {
            return null;
        }

        EventCreateDTO eventCreateDTO = new EventCreateDTO();

        eventCreateDTO.setDescription( eventEntity.getDescription() );
        eventCreateDTO.setLog( eventEntity.getLog() );
        eventCreateDTO.setLevel( eventEntity.getLevel() );
        eventCreateDTO.setQuantity( eventEntity.getQuantity() );

        return eventCreateDTO;
    }

    @Override
    public EventEntity map(EventCreateDTO eventCreateDTO, ApplicationEntity applicationEntity) {
        if ( eventCreateDTO == null && applicationEntity == null ) {
            return null;
        }

        EventEntity eventEntity = new EventEntity();

        if ( eventCreateDTO != null ) {
            eventEntity.setDescription( eventCreateDTO.getDescription() );
            eventEntity.setLog( eventCreateDTO.getLog() );
            eventEntity.setLevel( eventCreateDTO.getLevel() );
            eventEntity.setQuantity( eventCreateDTO.getQuantity() );
        }
        if ( applicationEntity != null ) {
            eventEntity.setApplication( applicationEntity );
            eventEntity.setCreatedAt( applicationEntity.getCreatedAt() );
        }

        return eventEntity;
    }
}
