import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personais")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping("/{id}")
    public ResponseEntity<Personal> obterPersonalPorId(@PathVariable Long id) {
        ResponseEntity<Personal> response = personalService.obterPersonalPorId(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Personal>> listarPersonais(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<Personal> personaisPage = personalService.listarPersonaisPaginados(pageRequest);

        if (personaisPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(personaisPage.getContent());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarPersonal(@RequestBody Personal personal) {
        ResponseEntity<String> response = personalService.cadastrarPersonal(personal);
        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.badRequest().body(response.getBody());
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPersonal(@PathVariable Long id, @RequestBody Personal personal) {
        ResponseEntity<String> response = personalService.atualizarPersonal(id, personal);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPersonal(@PathVariable Long id) {
        ResponseEntity<String> response = personalService.deletarPersonal(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return response;
    }
}
