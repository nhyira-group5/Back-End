package API.nhyira.Crud.Controller;

import API.nhyira.Crud.Model.PersonalModel;
import API.nhyira.Crud.Service.PersonalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personais")
public class PersonalController {
    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @PostMapping
    public ResponseEntity<String> criarPersonal(@RequestBody @Valid PersonalModel personal) {
        try {
            boolean personalAdicionado = personalService.adicionarPersonal(personal);
            if (personalAdicionado) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Personal registrado!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível registrar o personal!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonalModel>> buscarPersonais() {
        return personalService.getPersonais().isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(personalService.getPersonais());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPersonalPorId(@PathVariable int id) {
        try {
            Optional<PersonalModel> personal = personalService.getPersonaisPorId(id);
            return personal.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(personal) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar personal");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPersonal(@PathVariable int id, @RequestBody @Valid PersonalModel personal) {
        try {
            PersonalModel updatedPersonal = personalService.atualizarPersonal(id, personal);
            if (updatedPersonal != null) {
                return ResponseEntity.ok("Personal atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar personal: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPersonal(@PathVariable int id) {
        try {
            personalService.deletarPersonal(id);
            return ResponseEntity.ok("Personal excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao excluir personal: " + e.getMessage());
        }
    }
}