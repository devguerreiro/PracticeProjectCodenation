package br.com.ErrorCenter.dtos;

import br.com.ErrorCenter.entities.ApplicationEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginDTO implements UserDetails {

    private final ApplicationEntity applicationEntity;

    public LoginDTO(ApplicationEntity applicationEntity) {
        this.applicationEntity = applicationEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return applicationEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return applicationEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
