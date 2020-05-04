package br.com.ErrorCenter.services;

import br.com.ErrorCenter.dtos.ApplicationDTO;
import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.exceptions.event.EmailAlreadyUsedException;
import br.com.ErrorCenter.mappers.ApplicationMapper;
import br.com.ErrorCenter.repositories.ApplicationRepository;
import br.com.ErrorCenter.services.impl.ApplicationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private ApplicationMapper applicationMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private ApplicationServiceImpl applicationService;

    final Long APPLICATION_ID = 323L;
    final String APPLICATION_EMAIL = "teste@teste.com";
    final String APPLICATION_PASSWORD = "teste123";

    @Test
    public void save() {
        ApplicationEntity applicationEntity = mock(ApplicationEntity.class);
        when(applicationEntity.getId()).thenReturn(APPLICATION_ID);
        when(applicationEntity.getEmail()).thenReturn(APPLICATION_EMAIL);

        String passwordEncoded = passwordEncoder.encode(APPLICATION_PASSWORD);
        when(applicationEntity.getPassword()).thenReturn(passwordEncoded);

        ApplicationDTO applicationDTO = mock(ApplicationDTO.class);
        when(applicationDTO.getId()).thenReturn(APPLICATION_ID);

        when(applicationRepository.save(any(ApplicationEntity.class))).thenReturn(applicationEntity);
        when(applicationMapper.map(any(ApplicationEntity.class))).thenReturn(applicationDTO);
        Assertions.assertThat(applicationService.save(applicationEntity).getId()).isEqualTo(applicationEntity.getId());

        when(applicationRepository.findById(anyLong())).thenReturn(Optional.of(applicationEntity));
        Assertions.assertThat(
                applicationRepository.findById(
                        applicationService.save(applicationEntity).getId()).get()
                        .getPassword()).isEqualTo(passwordEncoded);

        when(applicationRepository.findByEmail(anyString())).thenReturn(Optional.of(applicationEntity));
        Assertions.assertThatThrownBy(() -> applicationService.save(applicationEntity)).isInstanceOf(EmailAlreadyUsedException.class);
    }

}
