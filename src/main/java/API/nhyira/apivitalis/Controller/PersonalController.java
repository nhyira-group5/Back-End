package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Personal.PersonalCreateDto;
import API.nhyira.apivitalis.DTO.Personal.PersonalExibitionDto;
import API.nhyira.apivitalis.DTO.Personal.PersonalMapper;
import API.nhyira.apivitalis.Repository.PersonalRepository;
import API.nhyira.apivitalis.Service.PersonalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personais")
public class PersonalController {

    @Autowired
    private PersonalService pService;

    @PostMapping
    public ResponseEntity<PersonalExibitionDto> create(@RequestBody @Valid PersonalCreateDto newPersonal){
        if (pService.cpfUnique(newPersonal.getCpf()) || pService.nomeUnique(newPersonal.getUsername()) || pService.emailUnique(newPersonal.getEmail())){
            return ResponseEntity.status(409).build();
        }

        PersonalExibitionDto personal = pService.createUser(newPersonal);
        if (personal == null){
            return  ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(201).body(personal);
    }

    @GetMapping
    public ResponseEntity<List<PersonalExibitionDto>> showAll(){
        return pService.showAllPersonals().isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(pService.showAllPersonals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalExibitionDto> showPersonal(@PathVariable int id){
        return pService.showUserById(id) == null ? ResponseEntity.status(404).build() : ResponseEntity.status(200).body(pService.showUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalExibitionDto> update(@PathVariable int id, @RequestBody @Valid PersonalCreateDto updtPersonal){
        if (pService.cpfUnique(updtPersonal.getCpf()) || pService.nomeUnique(updtPersonal.getUsername()) ||
        pService.emailUnique(updtPersonal.getEmail())){
            return ResponseEntity.status(409).build();
        }
        PersonalExibitionDto updatePersonal = pService.updPersonal(id, updtPersonal);
        if (updatePersonal == null){
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(200).body(updatePersonal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return !pService.delPersonal(id)? ResponseEntity.status(404).body("Usuário não encontrado!") :
                ResponseEntity.status(200).body("Usuário excluido com sucesso!");
    }
}
