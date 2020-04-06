package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.entities.EventEntity;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<EventEntity> findAll();

    Optional<EventEntity> findById(Long eventId);

}
