package br.com.ErrorCenter.services;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.dtos.EventCreateDTO;
import br.com.ErrorCenter.dtos.EventDetailDTO;
import br.com.ErrorCenter.dtos.EventListDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.mappers.ApplicationMapper;
import br.com.ErrorCenter.mappers.EventCreateMapper;
import br.com.ErrorCenter.mappers.EventDetailMapper;
import br.com.ErrorCenter.mappers.EventListMapper;
import br.com.ErrorCenter.repositories.ApplicationRepository;
import br.com.ErrorCenter.repositories.EventRepository;
import br.com.ErrorCenter.services.impl.EventServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private EventListMapper eventListMapper;
    @Mock
    private EventDetailMapper eventDetailMapper;
    @Mock
    private EventCreateMapper eventCreateMapper;
    @Mock
    private ApplicationMapper applicationMapper;
    @InjectMocks
    EventServiceImpl eventService;

    final Long EVENT_ID = 12L;
    final String EVENT_DESCRIPTION = "teste";

    final Long APPLICATION_ID = 129L;
    final String APPLICATION_EMAIL = "teste@teste.com";

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
        Assertions.assertThatThrownBy(() -> eventService.findById(-1L)).isInstanceOf(ResponseStatusException.class);

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
        when(eventDetailDTO.getOrigin()).thenReturn(applicationDTO);
        when(eventDetailMapper.map(eventEntity, applicationDTO)).thenReturn(eventDetailDTO);

        Assertions.assertThat(eventService.findById(EVENT_ID)).isInstanceOf(eventDetailDTO.getClass());
        Assertions.assertThat(eventService.findById(EVENT_ID).getId()).isEqualTo(eventEntity.getId());
        Assertions.assertThat(eventService.findById(EVENT_ID).getOrigin().getId()).isEqualTo(eventEntity.getApplication().getId());
        Assertions.assertThatThrownBy(() -> eventService.findById(-1L)).isInstanceOf(ResponseStatusException.class);
    }

    @Test
    public void save() {
        ApplicationEntity applicationEntity = mock(ApplicationEntity.class);
        when(applicationEntity.getId()).thenReturn(APPLICATION_ID);
        when(applicationRepository.findByEmail(anyString())).thenReturn(Optional.of(applicationEntity));

        ApplicationDTO applicationDTO = mock(ApplicationDTO.class);
        when(applicationDTO.getId()).thenReturn(APPLICATION_ID);
        when(applicationMapper.map(any(ApplicationEntity.class))).thenReturn(applicationDTO);

        EventCreateDTO eventCreateDTO = mock(EventCreateDTO.class);
        when(eventCreateDTO.getDescription()).thenReturn(EVENT_DESCRIPTION);

        EventEntity eventEntity = mock(EventEntity.class);
        when(eventEntity.getId()).thenReturn(EVENT_ID);
        when(eventEntity.getApplication()).thenReturn(applicationEntity);
        when(eventEntity.getDescription()).thenReturn(EVENT_DESCRIPTION);
        when(eventCreateMapper.map(any(EventCreateDTO.class), any(ApplicationEntity.class))).thenReturn(eventEntity);

        EventDetailDTO eventDetailDTO = mock(EventDetailDTO.class);
        when(eventDetailDTO.getId()).thenReturn(EVENT_ID);
        when(eventDetailDTO.getOrigin()).thenReturn(applicationDTO);
        when(eventDetailDTO.getDescription()).thenReturn(EVENT_DESCRIPTION);
        when(eventDetailMapper.map(any(EventEntity.class), any(ApplicationDTO.class))).thenReturn(eventDetailDTO);

        Assertions.assertThat(eventService.save(eventCreateDTO, APPLICATION_EMAIL)).isInstanceOf(EventDetailDTO.class);
        Assertions.assertThat(eventService.save(eventCreateDTO, APPLICATION_EMAIL).getId()).isEqualTo(eventEntity.getId());
        Assertions.assertThat(eventService.save(eventCreateDTO, APPLICATION_EMAIL).getDescription()).isEqualTo(eventEntity.getDescription());
        Assertions.assertThat(eventService.save(eventCreateDTO, APPLICATION_EMAIL).getOrigin().getId()).isEqualTo(eventEntity.getApplication().getId());
    }

}
