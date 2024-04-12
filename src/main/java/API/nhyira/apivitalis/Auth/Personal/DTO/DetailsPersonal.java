package API.nhyira.apivitalis.Auth.Personal.DTO;

import API.nhyira.apivitalis.Entity.Personal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class DetailsPersonal implements UserDetails {
    private final Personal personal;

    public DetailsPersonal(Personal personal) {
        this.personal = personal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return personal.getSenha();
    }

    @Override
    public String getUsername() {
        return personal.getUsername();
    }

    public String getEmail() {
        return personal.getEmail();
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
