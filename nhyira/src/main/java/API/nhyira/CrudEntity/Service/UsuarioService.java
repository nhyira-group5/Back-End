package API.nhyira.CrudEntity.Service;

import API.nhyira.CrudEntity.DATABASE.UsuarioRepository;
import API.nhyira.CrudEntity.Model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Boolean adicionarUsuario(UsuarioModel usuario) {
        if (usuario != null) {
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    public UsuarioModel atualizarUsuario(int id, UsuarioModel usuarioDetails) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            UsuarioModel usuario = usuarioOptional.get();

            try {
                usuario.setNome(usuarioDetails.getNome());
                usuario.setUsername(usuarioDetails.getUsername());
                usuario.setEmail(usuarioDetails.getEmail());
                usuario.setSenha(usuarioDetails.getSenha());
                usuario.setCpf(usuarioDetails.getCpf());
                usuario.setDtNasc(usuarioDetails.getDtNasc());
                usuario.setGenero(usuarioDetails.getGenero());
                usuario.setEmail2(usuarioDetails.getEmail2());
                usuario.setPeso(usuarioDetails.getPeso());
                usuario.setAltura(usuarioDetails.getAltura());

                adicionarUsuario(usuario);
                return usuario;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Usuário não encontrado");
        }
    }

    public Boolean deletarUsuario(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        throw new NoSuchElementException("Usuário não encontrado");
    }

    public List<UsuarioModel> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> getUsuarioPorId(int id) {
        return usuarioRepository.findById(id);
    }


    public boolean nomeUnique(String username) {
        return usuarioRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    public boolean emailUnique(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email).isEmpty();
    }

    public boolean cpfUnique(String cpf) {
        return usuarioRepository.findByCpf(cpf) == null;
    }
}


