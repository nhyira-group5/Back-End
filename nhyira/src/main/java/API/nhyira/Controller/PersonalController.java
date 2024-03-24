package API.nhyira.Controller;

import API.nhyira.Model.PersonalModel;
import API.nhyira.Service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Endpoint para excluir um personal
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirPersonal(@PathVariable("id") Long id) {
        personalService.excluirPersonal(id); // Excluir personal
        return ResponseEntity.noContent().build();
    }
}
