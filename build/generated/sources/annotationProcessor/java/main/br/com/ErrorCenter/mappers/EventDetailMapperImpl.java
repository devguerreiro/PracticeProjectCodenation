package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.entities.EventEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-04T14:40:25-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class EventDetailMapperImpl implements EventDetailMapper {

    @Override
    public EventDetailDTO map(EventEntity eventEntity, ApplicationDTO applicationDTO) {
        if ( eventEntity == null && applicationDTO == null ) {
            return null;
        }

        EventDetailDTO eventDetailDTO = new EventDetailDTO();

        if ( eventEntity != null ) {
            eventDetailDTO.setCreated_at( eventEntity.getCreatedAt() );
            eventDetailDTO.setId( eventEntity.getId() );
            eventDetailDTO.setDescription( eventEntity.getDescription() );
            eventDetailDTO.setLevel( eventEntity.getLevel() );
            eventDetailDTO.setLog( eventEntity.getLog() );
            if ( eventEntity.getQuantity() != null ) {
                eventDetailDTO.setQuantity( eventEntity.getQuantity() );
            }
        }
        if ( applicationDTO != null ) {
            eventDetailDTO.setOrigin( applicationDTO );
        }

        return eventDetailDTO;
    }
}
