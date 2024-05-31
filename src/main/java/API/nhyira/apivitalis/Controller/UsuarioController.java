package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioCreateEditDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioExibitionDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioMapper;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Service.CsvService;
import API.nhyira.apivitalis.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService uService;

    @Autowired
    private CsvService csvService;

    @PostMapping
    public ResponseEntity<UsuarioExibitionDto> create(@RequestBody @Valid UsuarioCreateEditDto newUser) {
        Usuario user = UsuarioMapper.toDto(newUser);
        Usuario userNovo = this.uService.createUser(user);
        UsuarioExibitionDto exibitionDto = UsuarioMapper.toExibition(userNovo);
        URI uri = URI.create("/usuarios/" + exibitionDto.getId());
        return ResponseEntity.created(uri).body(exibitionDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioExibitionDto>> showAll() {
        List<Usuario> users = uService.showAllUsers();
        List<UsuarioExibitionDto> exibitionDto = UsuarioMapper.toExibitionList(users);
        return ResponseEntity.ok(exibitionDto);
    }

    @GetMapping("/personais")
    public ResponseEntity<List<UsuarioExibitionDto>> showAllPersonal() {
        List<Usuario> personais = uService.showAllUsersPersonal();
        List<UsuarioExibitionDto> exibitionDto = UsuarioMapper.toExibitionList(personais);
        return ResponseEntity.ok(exibitionDto);
    }

    @GetMapping("/export/csv")
    public ResponseEntity<String> exportToCsv() {
        try {
            csvService.exportUsersToCsv();
            return ResponseEntity.status(200).body("Arquivo CSV gerado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao gerar o arquivo CSV.");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibitionDto> showUser (@PathVariable int id) {
        Usuario user = uService.showUserById(id);
        UsuarioExibitionDto exibitionDto = UsuarioMapper.toExibition(user);
        return ResponseEntity.ok(exibitionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioExibitionDto> update(@PathVariable int id,@RequestBody @Valid UsuarioCreateEditDto updtUser ) {
        Usuario updatedUser = uService.updtUser(id, updtUser);
        UsuarioExibitionDto exibitionDto = UsuarioMapper.toExibition(updatedUser);
        return ResponseEntity.status(200).body(exibitionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean user = uService.delUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-username")
    public ResponseEntity<UsuarioExibitionDto> buscarUsuarioPorUsername(@RequestParam String username) {
        UsuarioExibitionDto usuario = uService.findUserByUsername(username);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();

        }
    }
}
