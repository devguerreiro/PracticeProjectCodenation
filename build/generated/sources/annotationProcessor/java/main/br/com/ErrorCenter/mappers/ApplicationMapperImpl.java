package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-04T14:40:25-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class ApplicationMapperImpl implements ApplicationMapper {

    @Override
    public ApplicationDTO map(ApplicationEntity applicationEntity) {
        if ( applicationEntity == null ) {
            return null;
        }

        ApplicationDTO applicationDTO = new ApplicationDTO();

        applicationDTO.setCreated_at( applicationEntity.getCreatedAt() );
        applicationDTO.setId( applicationEntity.getId() );
        applicationDTO.setName( applicationEntity.getName() );

        return applicationDTO;
    }

    @Override
    public List<ApplicationDTO> map(List<ApplicationEntity> applicationEntities) {
        if ( applicationEntities == null ) {
            return null;
        }

        List<ApplicationDTO> list = new ArrayList<ApplicationDTO>( applicationEntities.size() );
        for ( ApplicationEntity applicationEntity : applicationEntities ) {
            list.add( map( applicationEntity ) );
        }

        return list;
    }
}
