package API.nhyira.Auth.Service;

import API.nhyira.Auth.Data.DetailsUsuarioData;
import API.nhyira.CrudEntity.DATABASE.UsuarioRepository;
import API.nhyira.CrudEntity.Model.UsuarioModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailsUsuarioServiceImp implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetailsUsuarioServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario = usuarioRepository.findByUsernameIgnoreCase(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
        }
        return new DetailsUsuarioData(usuario);
    }
}
