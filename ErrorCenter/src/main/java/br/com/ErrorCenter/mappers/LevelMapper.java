package br.com.ErrorCenter.mappers;

import br.com.ErrorCenter.dtos.LevelDTO;
import br.com.ErrorCenter.entities.LevelEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LevelMapper {

    LevelDTO mapToDto(LevelEntity levelEntity);

    List<LevelDTO> mapToDto(List<LevelEntity> levelEntity);

}
