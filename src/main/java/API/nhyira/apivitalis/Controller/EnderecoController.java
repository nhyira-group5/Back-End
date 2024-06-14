package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Endereco.AcademiaDto;

import API.nhyira.apivitalis.DTO.Endereco.EnderecoCreateEditDto;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoExibitionDto;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoMapper;
import API.nhyira.apivitalis.Entity.Endereco;
import API.nhyira.apivitalis.GooglePlaces.Service.AcademiasProximasService;
import API.nhyira.apivitalis.Service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;
    private final AcademiasProximasService academiasProximasService;

    @PostMapping
    public ResponseEntity<EnderecoExibitionDto> criarEndereco(@RequestBody @Valid EnderecoCreateEditDto dto) {
        Endereco endereco = EnderecoMapper.toEntity(dto);
        Endereco enderecoCriado = enderecoService.create(endereco);
        URI uri = URI.create("/enderecos/" + enderecoCriado.getIdEndereco());
        EnderecoExibitionDto exibitionDto = EnderecoMapper.toDto(enderecoCriado);
        return ResponseEntity.created(uri).body(exibitionDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoExibitionDto> mostrarEndereco(@PathVariable int id) {
        Endereco endereco = enderecoService.showEndereco(id);
        EnderecoExibitionDto exibitionDto = EnderecoMapper.toDto(endereco);
        return ResponseEntity.ok(exibitionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoExibitionDto> atualizarEndereco(@PathVariable int id, @RequestBody EnderecoCreateEditDto dto) {
        Endereco enderecoAtualizado = enderecoService.updateEndereco(id, dto);
        EnderecoExibitionDto exibitionDto = EnderecoMapper.toDto(enderecoAtualizado);
        return ResponseEntity.ok(exibitionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarEndereco(@PathVariable int id) {
        boolean deletado = enderecoService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/academias/proximas")
    public ResponseEntity<List<AcademiaDto>> buscarAcademiasProximas(@RequestParam String logradouro, @RequestParam String bairro, @RequestParam String cidade, @RequestParam String estado, @RequestParam String cep) {
        return academiasProximasService.buscarAcademiasProximas(logradouro, bairro, cidade, estado,cep);
    }
}