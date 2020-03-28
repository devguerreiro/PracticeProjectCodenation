package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.entities.LevelEntity;

import java.util.List;
import java.util.Optional;

public interface LevelService {

    List<LevelEntity> findAll();

    Optional<LevelEntity> findById(Long levelId);

    LevelEntity save(LevelEntity levelEntity);

}
