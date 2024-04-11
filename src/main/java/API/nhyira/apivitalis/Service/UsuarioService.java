package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Auth.Configuration.Security.TokenGenJwt;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioLoginDto;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioCreateEditDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioExibitionDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioMapper;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository uRep;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenGenJwt tokenGenJwt;

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLogin) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLogin.getLogin(), usuarioLogin.getSenha()
        );

        final Authentication auth = authenticationManager.authenticate(credentials);

        Optional<Usuario> usuarioByEmail = uRep.findByEmailIgnoreCase(usuarioLogin.getLogin());
        Optional<Usuario> usuarioByUsername = uRep.findByUsername(usuarioLogin.getLogin());

        final String token = tokenGenJwt.generateToken(auth);
        Usuario user = null;

        if (usuarioByEmail.isPresent()) {
            user = usuarioByEmail.get();
        } else if (usuarioByUsername.isPresent()) {
            user = usuarioByUsername.get();
        } else {
            throw new ResponseStatusException(404, "Credencial de login do usuário não cadastrado!", null);
        }

        SecurityContextHolder.getContext().setAuthentication(auth);
        return UsuarioMapper.of(user, token);
    }

    public UsuarioExibitionDto createUser(UsuarioCreateEditDto usuario) {
        try {
            if (usuario != null) {
                Usuario newUser = UsuarioMapper.toDto(usuario);
                newUser.setSenha(encoder.encode(newUser.getSenha()));
                uRep.save(newUser);

                return UsuarioMapper.toExibition(newUser);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar o usuário: " + e.getMessage());
        }
        return null;
    }

    public List<UsuarioExibitionDto> showAllUsers() {
        List<UsuarioExibitionDto> allUsers;
        try {
            allUsers =
                    uRep.findAll().stream()
                            .map(UsuarioMapper::toExibition).toList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar os usuários: " + e.getMessage());
        }
        return allUsers;
    }

    public UsuarioExibitionDto showUserById(int id) {
        try {
            Optional<Usuario> usuario = uRep.findById(id);

            if (usuario.isPresent()) {
                return UsuarioMapper.toExibition(usuario.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    public UsuarioExibitionDto updtUser(int id, UsuarioCreateEditDto updatedUser) {
        try {
            if (uRep.existsById(id)) {
                Usuario userSave = UsuarioMapper.toEditDto(uRep.findById(id).get(), updatedUser);
                userSave.setSenha(encoder.encode(userSave.getSenha()));

                UsuarioExibitionDto user = UsuarioMapper.toExibition(userSave);
                uRep.save(userSave);
                return user;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar o usuário: " + e.getMessage());
        }
        return null;
    }

    public boolean delUser(int id) {
        try {
            if (uRep.existsById(id)) {
                uRep.deleteById(id);
                return true;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage());
        }
        return false;
    }

    public boolean nomeUnique(String username) {
        return uRep.findByUsername(username).isPresent();
    }

    public boolean emailUnique(String email) {
        return uRep.findByEmailIgnoreCase(email).isPresent();
    }

    public boolean cpfUnique(String cpf) {
        return uRep.findByCpf(cpf).isPresent();
    }
}
