package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioCreateDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioExibitionDto;
import API.nhyira.apivitalis.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService uService;

    @PostMapping
    public ResponseEntity<UsuarioExibitionDto> create(
            @RequestBody @Valid UsuarioCreateDto newUser
            ) {
        if (uService.cpfUnique(newUser.getCpf()) || uService.nomeUnique(newUser.getUsername()) || uService.emailUnique(newUser.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        UsuarioExibitionDto user = uService.createUser(newUser);
        if(user == null) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(201).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioExibitionDto>> showAll() {
        return uService.showAllUsers().isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(uService.showAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibitionDto> showUser (
            @PathVariable int id
    ) {
        return uService.showUserById(id) == null ? ResponseEntity.status(404).build() : ResponseEntity.status(200).body(uService.showUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioExibitionDto> update(
            @PathVariable int id,
            @RequestBody @Valid UsuarioCreateDto updtUser
    ) {
        if (uService.cpfUnique(updtUser.getCpf()) || uService.nomeUnique(updtUser.getUsername()) || uService.emailUnique(updtUser.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        UsuarioExibitionDto updatedUser = uService.updtUser(id, updtUser);
        if (updatedUser == null) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(200).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (
            @PathVariable int id
    ) {
        return !uService.delUser(id) ? ResponseEntity.status(404).body("Usuário não encontrado!") : ResponseEntity.status(200).body("Usuário excluido com sucesso!");
    }
}
