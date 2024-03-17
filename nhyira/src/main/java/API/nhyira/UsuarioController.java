package API.nhyira;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioModel usuario) {
        try {
            usuarioService.validarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @GetMapping("/listarPorMeta")
    public ResponseEntity<Map<String, List<UsuarioModel>>> listarUsuariosPorMeta() {
        try {
            Map<String, List<UsuarioModel>> usuariosPorMeta = usuarioService.listarUsuariosPorMeta();
            return ResponseEntity.ok(usuariosPorMeta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<>());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioModel> buscarUsuario(@PathVariable int id) {
        UsuarioModel usuario = new UsuarioModel();
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable int id, @RequestBody UsuarioModel usuario) {
        try {
            usuarioService.validarUsuario(usuario);
            UsuarioModel updatedUsuario = usuarioService.atualizarUsuario(id, usuario);
            if (updatedUsuario != null) {
                return ResponseEntity.ok("Usuário atualizado com sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable int id) {
        return ResponseEntity.ok("Usuário excluído com sucesso");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        List<UsuarioModel> usuarios = null;
        return ResponseEntity.ok(usuarios);
    }
}
