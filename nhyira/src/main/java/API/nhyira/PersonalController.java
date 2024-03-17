package API.nhyira;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    // Endpoint para criar um novo personal

    @PostMapping("/criar")
    public ResponseEntity<?> criarPersonal(@RequestBody PersonalModel personal) {
        try {
            personalService.criarPersonal(personal);
            return ResponseEntity.status(HttpStatus.CREATED).body("Personal criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar personal: " + e.getMessage());
        }
    }


    // Endpoint para obter todos os personals de diferentes especialidades ordenados por nome
    @GetMapping("/todos")
    public ResponseEntity<Map<String, List<PersonalModel>>> obterPersonalsPorEspecialidadeOrdenadosPorNome() {
        // Obtém todos os personals ordenados por especialidade
        List<PersonalModel> personalsOrdenados = personalService.obterTodosPersonalsOrdenadosPorEspecialidade();

        // Agrupa os personals por especialidade
        Map<String, List<PersonalModel>> personalsPorEspecialidade = new HashMap<>();
        for (PersonalModel personal : personalsOrdenados) {
            String especialidade = personal.getEspecialidade();
            if (!personalsPorEspecialidade.containsKey(especialidade)) {
                personalsPorEspecialidade.put(especialidade, new ArrayList<>());
            }
            personalsPorEspecialidade.get(especialidade).add(personal);
        }

        return ResponseEntity.ok(personalsPorEspecialidade);
    }

    // Endpoint para obter um personal pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obterPersonalPorId(@PathVariable("id") Long id) {
        PersonalModel personal = personalService.obterPersonalPorId(id);
        if (personal != null) {
            return ResponseEntity.ok(personal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para atualizar um personal
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarPersonal(@PathVariable("id") Long id, @RequestBody PersonalModel personalDetalhes) {
        try {
            personalService.validarPersonal(personalDetalhes); // Validar personal antes de atualizar
            PersonalModel personalAtualizado = personalService.atualizarPersonal(id, personalDetalhes); // Atualizar personal
            if (personalAtualizado != null) {
                return ResponseEntity.ok(personalAtualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para excluir um personal
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirPersonal(@PathVariable("id") Long id) {
        personalService.excluirPersonal(id); // Excluir personal
        return ResponseEntity.noContent().build();
    }
}
