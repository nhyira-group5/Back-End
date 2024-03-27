package API.nhyira.Controller;

import API.nhyira.Model.PersonalModel;
import API.nhyira.Service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD:nhyira/src/main/java/API/nhyira/PersonalController.java
import java.util.*;
=======
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
>>>>>>> fd49bcc327a8e63ede2ddc3dc430ab34102c685c:nhyira/src/main/java/API/nhyira/Controller/PersonalController.java

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    // Endpoint para criar um novo personal
    @PostMapping("/criar")
    public ResponseEntity<?> criarPersonal(@RequestBody PersonalModel personal) {
        try {
<<<<<<< HEAD:nhyira/src/main/java/API/nhyira/PersonalController.java
            personalService.criarPersonal(personal);
            return ResponseEntity.status(HttpStatus.CREATED).body("Personal criado com sucesso");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("CPF duplicado") || e.getMessage().equals("Email duplicado")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF ou email já cadastrado");
            } else if (e.getMessage().equals("CPF e email duplicados")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF e email já cadastrados");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar personal: " + e.getMessage());
        }
    }


    // Endpoint para obter todos os personals de diferentes especialidades ordenados por nome
    @GetMapping("/todos")
    public ResponseEntity<Map<String, List<PersonalModel>>> obterPersonalsPorEspecialidadeOrdenadosPorNome() {
        Map<String, List<PersonalModel>> personalsPorEspecialidade = personalService.obterTodosPersonalsOrdenadosPorEspecialidade();
        return ResponseEntity.ok(personalsPorEspecialidade);
    }

    // Endpoint para obter todos os personals
    @GetMapping("/listar")
    public ResponseEntity<List<PersonalModel>> obterTodosPersonals() {
        List<PersonalModel> todosPersonals = personalService.obterTodosPersonals();
        return ResponseEntity.ok(todosPersonals);
=======
            PersonalModel createdPersonal = personalService.criarPersonal(personal);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPersonal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Endpoint para obter um personal pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obterPersonalPorId(@PathVariable("id") Long id) {
        PersonalModel personal = personalService.obterPersonalPorId(id);
        if (personal != null) {
            return ResponseEntity.ok(personal);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
>>>>>>> fd49bcc327a8e63ede2ddc3dc430ab34102c685c:nhyira/src/main/java/API/nhyira/Controller/PersonalController.java
    }

    // Endpoint para atualizar um personal
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarPersonal(@PathVariable("id") Long id, @RequestBody PersonalModel personalDetalhes) {
        try {
            if (personalService.existePersonal(id)) {
                personalDetalhes.setId(id);
                PersonalModel personalAtualizado = personalService.atualizarPersonal(personalDetalhes);

                if (personalAtualizado != null) {
                    return ResponseEntity.ok(personalAtualizado);

                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
<<<<<<< HEAD:nhyira/src/main/java/API/nhyira/PersonalController.java
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
=======
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
>>>>>>> fd49bcc327a8e63ede2ddc3dc430ab34102c685c:nhyira/src/main/java/API/nhyira/Controller/PersonalController.java
        }
    }


    // Endpoint para excluir um personal
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirPersonal(@PathVariable("id") Long id) {
        try {
            personalService.excluirPersonal(id); // Excluir personal
            return ResponseEntity.ok("Usuário deletado com sucesso");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
