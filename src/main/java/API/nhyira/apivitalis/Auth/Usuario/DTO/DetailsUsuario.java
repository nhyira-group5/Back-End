package API.nhyira.apivitalis.Auth.Usuario.DTO;

import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class DetailsUsuario implements UserDetailsUsuario {


    private final String userName;
    private final String email;
    private final String senha;
    private final Usuario.TipoUsuario tipo;

    public DetailsUsuario(Usuario usuario) {
        this.userName = usuario.getNickname();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.tipo = usuario.getTipo();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return String.valueOf(tipo); // Retornar tipo como String
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
