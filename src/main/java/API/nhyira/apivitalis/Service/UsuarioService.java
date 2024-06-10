package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Auth.Configuration.Security.TokenGenJwt;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioLoginDto;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaMapper;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioCreateEditDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioExibitionDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioMapper;
import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.ConflitoException;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Exception.SemConteudoException;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository uRep;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManagerForUsuarios;
    private final TokenGenJwt tokenGenJwt;

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLogin) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLogin.getLogin(), usuarioLogin.getSenha()
        );

        final Authentication auth = this.authenticationManagerForUsuarios.authenticate(credentials);

        Optional<Usuario> usuarioByEmail = uRep.findByEmailIgnoreCase(usuarioLogin.getLogin());
        Optional<Usuario> usuarioByUsername = uRep.findByNickname(usuarioLogin.getLogin());
//        Optional<Usuario> usuarioByTipo = uRep.findByTipo(usuarioLogin.getLogin());
        Usuario.TipoUsuario tipo = null;
        if (usuarioByEmail.isPresent()) {
            tipo = usuarioByEmail.get().getTipo();
        } else if (usuarioByUsername.isPresent()) {
            tipo = usuarioByUsername.get().getTipo();
        } else {
            throw new ResponseStatusException(404, "Credencial de login do usuário não cadastrado!", null);
        }

        final String token = tokenGenJwt.generateToken((auth), tipo);
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

    public Usuario createUser(Usuario usuario) {
        if (usuario == null) {
            throw new ErroClienteException("Usuario");
        }
        if (cpfUnique(usuario.getCpf())) {
            throw new ConflitoException("CPF");
        }
        if (nomeUnique(usuario.getNickname())) {
            throw new ConflitoException("Nickname");
        }
        if (emailUnique(usuario.getEmail())) {
            throw new ConflitoException("Email");
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        uRep.save(usuario);
        return usuario;
    }

    public List<Usuario> showAllUsers() {
        List<Usuario> allUsers = uRep.buscarUsuarios();
        if (allUsers.isEmpty()) {
            throw new SemConteudoException("Usuarios");
        }
        return allUsers;
    }

    public List<Usuario> showAllUsersPersonal() {
        List<Usuario> allUsers = uRep.buscarPersonal();
        if (allUsers.isEmpty()) {
            throw new SemConteudoException("Personais");
        }
        return allUsers;
    }

    public Usuario showUserById(int id) {
        Optional<Usuario> usuario = uRep.findById(id);
        usuario.orElseThrow(() -> new NaoEncontradoException("usuario"));
        return usuario.get();
    }

    public Usuario updtUser(int id, UsuarioCreateEditDto updatedUser) {
        if (uRep.existsById(id)) {
            Optional<Usuario> user = uRep.findById(id);
            user.orElseThrow(() -> new NaoEncontradoException("usuario"));
            if (cpfUnique(updatedUser.getCpf())) {
                throw new ConflitoException("CPF");
            }
            if (nomeUnique(updatedUser.getNickname())) {
                throw new ConflitoException("UserName");
            }
            if (emailUnique(updatedUser.getEmail())) {
                throw new ConflitoException("Email");
            }
            updatedUser.setSenha(encoder.encode(updatedUser.getSenha()));
            Usuario usuario = UsuarioMapper.toEditDto(user.get(), updatedUser);
            uRep.save(usuario);
            return usuario;
        }
        throw new NaoEncontradoException("Id");
    }

    public boolean delUser(int id) {
        if (!uRep.existsById(id)) {
            throw new NaoEncontradoException("Id");
        }
        uRep.deleteById(id);
        return true;

    }

    public List<Usuario> getAllUsers() {
        return uRep.findAll();
    }

    public boolean nomeUnique(String username) {
        return uRep.findByNickname(username).isPresent();
    }

    public boolean emailUnique(String email) {
        return uRep.findByEmailIgnoreCase(email).isPresent();
    }

    public boolean cpfUnique(String cpf) {
        return uRep.findByCpf(cpf).isPresent();
    }

    public UsuarioExibitionDto findUserByUsername(String username) {
        try {
            List<Usuario> allUsers = uRep.buscarUsuarios();
            Collections.sort(allUsers, Comparator.comparing(Usuario::getNickname));
            int index = binarySearch(allUsers, username);
            if (index != -1) {
                Usuario user = allUsers.get(index);
                return UsuarioMapper.toExibition(user);
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar o usuário: " + e.getMessage());
        }
    }

    private int binarySearch(List<Usuario> usuarios, String usernameAlvo) {
        int esquerda = 0;
        int direita = usuarios.size() - 1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            String usernameAtual = usuarios.get(meio).getNickname();

            int comparacao = usernameAtual.compareTo(usernameAlvo);

            if (comparacao == 0) {
                return meio;
            } else if (comparacao < 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return -1;
    }
}
