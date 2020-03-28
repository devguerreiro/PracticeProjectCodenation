package br.com.ErrorCenter.services.impl;

import br.com.ErrorCenter.entities.LevelEntity;
import br.com.ErrorCenter.repositories.LevelRepository;
import br.com.ErrorCenter.services.interfaces.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public List<LevelEntity> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<LevelEntity> findById(Long levelId) {
        return levelRepository.findById(levelId);
    }

    @Override
    public LevelEntity save(LevelEntity levelEntity) {
        return levelRepository.save(levelEntity);
    }

}
