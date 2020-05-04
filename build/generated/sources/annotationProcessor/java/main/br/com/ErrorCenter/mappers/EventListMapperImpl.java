package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.entities.EventEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-04T03:23:42-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class EventListMapperImpl implements EventListMapper {

    @Override
    public EventListDTO map(EventEntity eventEntity) {
        if ( eventEntity == null ) {
            return null;
        }

        EventListDTO eventListDTO = new EventListDTO();

        eventListDTO.setCreated_at( eventEntity.getCreatedAt() );
        eventListDTO.setOrigin_id( eventEntityApplicationId( eventEntity ) );
        eventListDTO.setId( eventEntity.getId() );
        eventListDTO.setDescription( eventEntity.getDescription() );
        eventListDTO.setLevel( eventEntity.getLevel() );
        if ( eventEntity.getQuantity() != null ) {
            eventListDTO.setQuantity( eventEntity.getQuantity() );
        }

        return eventListDTO;
    }

    @Override
    public List<EventListDTO> map(List<EventEntity> eventEntities) {
        if ( eventEntities == null ) {
            return null;
        }

        List<EventListDTO> list = new ArrayList<EventListDTO>( eventEntities.size() );
        for ( EventEntity eventEntity : eventEntities ) {
            list.add( map( eventEntity ) );
        }

        return list;
    }

    private Long eventEntityApplicationId(EventEntity eventEntity) {
        if ( eventEntity == null ) {
            return null;
        }
        ApplicationEntity application = eventEntity.getApplication();
        if ( application == null ) {
            return null;
        }
        Long id = application.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
