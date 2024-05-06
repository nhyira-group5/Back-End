package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Endereco.AcademiaDto;

import API.nhyira.apivitalis.DTO.Endereco.EnderecoCreateEditDto;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoExibitionDto;
import API.nhyira.apivitalis.GooglePlaces.Service.AcademiasProximasService;
import API.nhyira.apivitalis.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private AcademiasProximasService academiasProximasService;



    @PostMapping
    public ResponseEntity<EnderecoExibitionDto> criarEndereco(@RequestBody EnderecoCreateEditDto dto) {
        try {
            EnderecoExibitionDto enderecoCriado = enderecoService.create(dto);
            if (enderecoCriado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCriado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoExibitionDto> mostrarEndereco(@PathVariable int id) {
        try {
            EnderecoExibitionDto endereco = enderecoService.showEndereco(id);
            if (endereco != null) {
                return ResponseEntity.status(HttpStatus.OK).body(endereco);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoExibitionDto> atualizarEndereco(@PathVariable int id, @RequestBody EnderecoCreateEditDto dto) {
        try {
            EnderecoExibitionDto enderecoAtualizado = enderecoService.updateEndereco(id, dto);
            if (enderecoAtualizado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(enderecoAtualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarEndereco(@PathVariable int id) {
        try {
            boolean deletado = enderecoService.deleteUser(id);
            if (deletado) {
                return ResponseEntity.status(HttpStatus.OK).body(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/academias/proximas")
    public ResponseEntity<List<AcademiaDto>> buscarAcademiasProximas(@RequestParam String logradouro, @RequestParam String bairro, @RequestParam String cidade, @RequestParam String estado, @RequestParam String cep) {
        return academiasProximasService.buscarAcademiasProximas(logradouro, bairro, cidade, estado,cep);
    }


}