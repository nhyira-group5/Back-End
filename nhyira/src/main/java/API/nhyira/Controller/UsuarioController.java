package API.nhyira.Controller;

import API.nhyira.DBA.UsuarioRepository;
import API.nhyira.Model.UsuarioModel;
import API.nhyira.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioModel usuario) {
        try {
            usuarioService.validarUsuario(usuario);
            return usuarioService.adicionarUsuario(usuario) ? ResponseEntity.status(HttpStatus.CREATED).body("Usuário registado!") : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível registrar o usuário!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioModel>> buscarUsuario(@PathVariable int id) {
        Optional<UsuarioModel> usuario = usuarioService.getUsuarioPorId(id);

        return usuario.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.status(200).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable int id, @RequestBody UsuarioModel usuario) {
        try {
            usuarioService.validarUsuario(usuario);
            UsuarioModel updatedUsuario = usuarioService.atualizarUsuario(id, usuario);

            if (updatedUsuario != null) {
                return ResponseEntity.ok("Usuário atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable int id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.ok("Usuário excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        List<UsuarioModel> usuariosEncontrados = usuarioService.getUsuarios();
        return usuariosEncontrados.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(usuariosEncontrados);
    }
}
