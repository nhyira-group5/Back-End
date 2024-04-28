package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Endereco.EnderecoCreateEditDto;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoProximosGoogleDto;
import API.nhyira.apivitalis.Entity.EnderecoModel;
import API.nhyira.apivitalis.GooglePlaces.Service.AcademiasProximasService;
import API.nhyira.apivitalis.Service.EnderecoService;
import jakarta.validation.Valid;
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

    @PostMapping("/api/academias/proximas")
    public ResponseEntity<String> buscarAcademiasProximas( @RequestBody @Valid EnderecoProximosGoogleDto enderecoDto) {
        String resultado = academiasProximasService.buscarAcademiasProximas(
                enderecoDto.getLogradouro(),
                enderecoDto.getBairro(),
                enderecoDto.getCidade(),
                enderecoDto.getEstado(),
                enderecoDto.getCep()
        );
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<EnderecoModel> adicionarEndereco(@RequestBody EnderecoModel endereco) {
        EnderecoModel novoEndereco = enderecoService.salvarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<EnderecoModel> buscarEnderecoPorId(@PathVariable Integer id) {
        Optional<EnderecoModel> enderecoOptional = enderecoService.buscarEnderecoPorId(id);
        return enderecoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EnderecoModel>> buscarTodosEnderecos() {
        List<EnderecoModel> enderecos = enderecoService.buscarTodosEnderecos();
        return ResponseEntity.ok(enderecos);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EnderecoModel> atualizarEndereco(@PathVariable Integer id, @RequestBody EnderecoModel novoEndereco) {
        EnderecoModel enderecoAtualizado = enderecoService.atualizarEndereco(id, novoEndereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
