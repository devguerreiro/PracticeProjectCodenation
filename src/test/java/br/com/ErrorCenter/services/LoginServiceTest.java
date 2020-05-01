package br.com.ErrorCenter.services;

import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.repositories.ApplicationRepository;
import br.com.ErrorCenter.services.impl.LoginServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;

import java.util.Locale;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private LoginServiceImpl loginService;

    final String LOGIN_USERNAME = "teste@teste.com";

    final String APPLICATION_EMAIL = "teste@teste.com";

    @Test
    public void loadUserByEmail() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(LOGIN_USERNAME);

        ApplicationEntity applicationEntity = mock(ApplicationEntity.class);
        when(applicationEntity.getEmail()).thenReturn(APPLICATION_EMAIL);

        when(applicationRepository.findByEmail(anyString())).thenReturn(Optional.of(applicationEntity));

        Assertions.assertThat(loginService.loadUserByEmail(APPLICATION_EMAIL)).isNotNull();
        Assertions.assertThat(loginService.loadUserByEmail(APPLICATION_EMAIL)).isInstanceOf(UserDetails.class);
        Assertions.assertThat(loginService.loadUserByEmail(APPLICATION_EMAIL).getUsername()).isEqualTo(applicationEntity.getEmail());

        when(applicationRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(
                "AbstractUserDetailsAuthenticationProvider.badCredentials",
                null,
                Locale.ENGLISH)
        ).thenReturn("qualquer mensagem para teste");
        Assertions.assertThatThrownBy(() -> loginService.loadUserByEmail(anyString())).isInstanceOf(InvalidGrantException.class);
    }

}
