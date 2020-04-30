package br.com.ErrorCenter.services;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.mappers.ApplicationMapper;
import br.com.ErrorCenter.mappers.EventDetailMapper;
import br.com.ErrorCenter.mappers.EventListMapper;
import br.com.ErrorCenter.repositories.EventRepository;
import br.com.ErrorCenter.services.impl.EventServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EventServiceTest {

    @Mock
    EventRepository eventRepository;

    @Mock
    EventListMapper eventListMapper;

    @Mock
    EventDetailMapper eventDetailMapper;

    @Mock
    ApplicationMapper applicationMapper;

    @InjectMocks
    EventServiceImpl eventService;

    final Long EVENT_ID = 12L;
    final String EVENT_DESCRIPTION = "teste";

    final Long APPLICATION_ID = 129L;

    @Test
    public void findByAny() {
        EventEntity eventEntity = mock(EventEntity.class);
        when(eventEntity.getId()).thenReturn(EVENT_ID);
        when(eventEntity.getDescription()).thenReturn(EVENT_DESCRIPTION);

        List<EventEntity> events = new ArrayList<>();
        events.add(eventEntity);

        when(eventRepository.findByAny(
                null,
                EVENT_DESCRIPTION,
                null,
                null,
                null,
                null,
                null)).thenReturn(new PageImpl<>(events));

        EventListDTO eventListDTO = mock(EventListDTO.class);
        when(eventListDTO.getId()).thenReturn(EVENT_ID);
        when(eventListDTO.getDescription()).thenReturn(EVENT_DESCRIPTION);
        when(eventListMapper.map(any(EventEntity.class))).thenReturn(eventListDTO);

        Assertions.assertThat(
                eventService.findByAny(
                        null,
                        EVENT_DESCRIPTION,
                        null,
                        null,
                        null,
                        null,
                        null).getContent().get(0))
                .isInstanceOf(eventListDTO.getClass());

        Assertions.assertThat(
                eventService.findByAny(
                        null,
                        EVENT_DESCRIPTION,
                        null,
                        null,
                        null,
                        null,
                        null).getContent().get(0).getDescription())
                .isEqualTo(eventEntity.getDescription());
    }

    @Test
    public void findById() {
        ApplicationEntity applicationEntity = mock(ApplicationEntity.class);
        when(applicationEntity.getId()).thenReturn(APPLICATION_ID);

        EventEntity eventEntity = mock(EventEntity.class);
        when(eventEntity.getId()).thenReturn(EVENT_ID);
        when(eventEntity.getApplication()).thenReturn(applicationEntity);

        when(eventRepository.findById(EVENT_ID)).thenReturn(Optional.of(eventEntity));

        ApplicationDTO applicationDTO = mock(ApplicationDTO.class);
        when(applicationDTO.getId()).thenReturn(APPLICATION_ID);
        when(applicationMapper.map(any(ApplicationEntity.class))).thenReturn(applicationDTO);

        EventDetailDTO eventDetailDTO = mock(EventDetailDTO.class);
        when(eventDetailDTO.getId()).thenReturn(EVENT_ID);
        when(eventDetailMapper.map(eventEntity, applicationDTO)).thenReturn(eventDetailDTO);

        Assertions.assertThat(eventService.findById(EVENT_ID)).isInstanceOf(eventDetailDTO.getClass());
        Assertions.assertThat(eventService.findById(EVENT_ID).getId()).isEqualTo(eventEntity.getId());
    }

    @Test
    public void save() {

    }

}
