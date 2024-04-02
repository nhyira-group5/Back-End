package API.nhyira.Auth.Service;

import API.nhyira.Auth.DTO.DetailsUsuario;
import API.nhyira.Crud.Repository.UsuarioRepository;
import API.nhyira.Crud.Model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuarioByUsername = usuarioRepository.findByUsernameIgnoreCase(login);
        Optional<UsuarioModel> usuarioByEmail = usuarioRepository.findByEmailIgnoreCase(login);

        if (usuarioByEmail.isPresent()) {
            return new DetailsUsuario(usuarioByEmail.get());
        } else if (usuarioByUsername.isPresent()) {
            return new DetailsUsuario(usuarioByUsername.get());
        }

        throw new UsernameNotFoundException("Usuário com a credencial " + login + " não encontrado!");
    }
}
