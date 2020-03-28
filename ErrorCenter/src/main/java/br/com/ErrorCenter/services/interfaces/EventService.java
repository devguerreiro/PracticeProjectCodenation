package br.com.ErrorCenter.services.interfaces;

import br.com.ErrorCenter.entities.EventEntity;

import java.util.Optional;
import java.util.Set;

public interface EventService {

    Set<EventEntity> findAll();

    Optional<EventEntity> findById(Long eventId);

}
