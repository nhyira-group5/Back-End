package API.nhyira;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioModel usuario) {
        try {
            usuarioService.validarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @GetMapping("/listarPorMeta")
    public ResponseEntity<?> listarUsuariosPorMeta() {
        try {
            Map<String, List<UsuarioModel>> usuariosPorMeta = usuarioService.listarUsuariosPorMeta();
            return ResponseEntity.ok(usuariosPorMeta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<>());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable long id) {
        try {
            int idInt = Math.toIntExact(id);
            UsuarioModel usuario = usuarioService.buscarUsuarioPorId(idInt);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().body("ID fornecido é muito grande para ser convertido para int.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar usuário: " + e.getMessage());
        }
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable long id, @RequestBody UsuarioModel usuario) {
        try {
            int idInt = Math.toIntExact(id);

            UsuarioModel existingUsuario = usuarioService.buscarUsuarioPorId(idInt);
            if (existingUsuario != null) {
                if (!usuario.equals(existingUsuario)) {
                    UsuarioModel updatedUsuario = usuarioService.atualizarUsuario(idInt, usuario);
                    if (updatedUsuario != null) {
                        return ResponseEntity.ok(updatedUsuario);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                } else {
                    return ResponseEntity.ok("Nenhum campo foi alterado");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().body("ID fornecido é muito grande para ser convertido para int.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }



    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioService.listarUsuarios(); // Obter a lista de usuários do serviço
        return ResponseEntity.ok(usuarios);
    }


    // Endpoint para excluir um usuário
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable long id) {
        try {
            int idInt = Math.toIntExact(id);
            boolean excluido = usuarioService.excluirUsuario(idInt);
            if (excluido) {
                return ResponseEntity.ok("Usuário excluído com sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().body("ID fornecido é muito grande para ser convertido para int.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}