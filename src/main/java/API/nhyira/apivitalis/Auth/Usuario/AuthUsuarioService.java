package API.nhyira.apivitalis.Auth.Usuario;

import API.nhyira.apivitalis.Auth.Usuario.DTO.DetailsUsuario;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UserDetailsUsuario;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository uRep;

    @Override
    public UserDetailsUsuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioByTipo = uRep.findByNickname(username);

        if (usuarioByTipo.isEmpty()) {
            throw new UsernameNotFoundException("Usuário com o tipo [" + username + "] não encontrado!");
        }

        return new DetailsUsuario(usuarioByTipo.get());
    }
}
