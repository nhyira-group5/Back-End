import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<Usuario> obterUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

   public ResponseEntity<String> cadastrarUsuario(Personal usuario) {
       
        if (personal.getNome() == null || personal.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome inválido");
        }
        if (personal.getEmail() == null || !personal.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            return ResponseEntity.badRequest().body("Email inválido");
        }
        if (personal.getSenha() == null || personal.getSenha().length() < 8) {
            return ResponseEntity.badRequest().body("A senha deve ter pelo menos 8 caracteres");
        }
        

    public ResponseEntity<String> atualizarUsuario(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id); //
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário atualizado com sucesso");
    }

    public ResponseEntity<String> deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }
}
