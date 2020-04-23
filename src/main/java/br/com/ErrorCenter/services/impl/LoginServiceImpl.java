package br.com.ErrorCenter.services.impl;

import br.com.ErrorCenter.dtos.LoginDTO;
import br.com.ErrorCenter.repositories.ApplicationRepository;
import br.com.ErrorCenter.services.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public UserDetails loadUserByEmail(String email) {
        return new LoginDTO(applicationRepository.findByEmail(email).stream()
                .findFirst()
                //não deve informar que o usuário não foi encontrado por questões de boas práticas de segurança
                .orElseThrow(() -> new InvalidGrantException("Bad credentials")));
    }

}
