package br.com.ErrorCenter.services.impl;

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
        return applicationRepository.findByEmail(email).stream()
                .findFirst()
                .orElseThrow(() -> new InvalidGrantException("Bad credentials"));
    }

}
