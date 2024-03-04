import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    public ResponseEntity<Personal> obterPersonalPorId(Long id) {
        Optional<Personal> personalOptional = personalRepository.findById(id);
        return personalOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public List<Personal> listarPersonais() {
        return personalRepository.findAll();
    }

    public List<Personal> listarPersonaisOrdenados(String campoOrdenacao) {
        return personalRepository.findAll(Sort.by(campoOrdenacao));
    }

    public ResponseEntity<String> cadastrarPersonal(Personal personal) {
       
        if (personal.getNome() == null || personal.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome inválido");
        }
        if (personal.getEmail() == null || !personal.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            return ResponseEntity.badRequest().body("Email inválido");
        }
        if (personal.getSenha() == null || personal.getSenha().length() < 8) {
            return ResponseEntity.badRequest().body("A senha deve ter pelo menos 8 caracteres");
        }
        

        personalRepository.save(personal);
        return ResponseEntity.ok("Personal cadastrado com sucesso");
    }

    public ResponseEntity<String> atualizarPersonal(Long id, Personal personal) {
        if (!personalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personal.setId(id);
        personalRepository.save(personal);
        return ResponseEntity.ok("Personal atualizado com sucesso");
    }

    public ResponseEntity<String> deletarPersonal(Long id) {
        if (!personalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personalRepository.deleteById(id);
        return ResponseEntity.ok("Personal deletado com sucesso");
    }
}
