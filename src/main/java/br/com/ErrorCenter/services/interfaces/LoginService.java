package br.com.ErrorCenter.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {

    UserDetails loadUserByEmail(String email);

}
