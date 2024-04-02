package API.nhyira.Crud.Controller;

import API.nhyira.Crud.Model.UsuarioModel;
import API.nhyira.Crud.Service.UsuarioService;
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
            boolean usuarioAdicionado = usuarioService.adicionarUsuario(usuario);
            if (usuarioAdicionado) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível registrar o usuário!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> buscarUsuarios() {
        return usuarioService.getUsuarios().isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(usuarioService.getUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable int id) {
        try {
            Optional<UsuarioModel> usuario = usuarioService.getUsuarioPorId(id);
            return usuario.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(usuario) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar usuário");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable int id, @RequestBody UsuarioModel usuario) {
        try {
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}