package API.nhyira.apivitalis.Auth.Usuario;

import API.nhyira.apivitalis.Auth.Usuario.DTO.DetailsUsuario;
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
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuarioByUsername = uRep.findByUsername(login);

        if (usuarioByUsername.isEmpty()) {
            Optional<Usuario> usuarioByEmail = uRep.findByEmailIgnoreCase(login);

            if (usuarioByEmail.isEmpty()) {
                throw new UsernameNotFoundException("Usuário com a credencial [" + login + "] não encontrado!");
            }
            return new DetailsUsuario(usuarioByEmail.get());
        }
        return new DetailsUsuario(usuarioByUsername.get());
    }


}
