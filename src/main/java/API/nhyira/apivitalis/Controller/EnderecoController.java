package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Endereco.AcademiaDto;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoProximosGoogleDto;
import API.nhyira.apivitalis.Entity.Endereco;
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
    public ResponseEntity<List<AcademiaDto>>  buscarAcademiasProximas(@RequestBody @Valid EnderecoProximosGoogleDto enderecoDto) {
        String logradouro = enderecoDto.getLogradouro();
        String bairro = enderecoDto.getBairro();
        String cidade = enderecoDto.getCidade();
        String estado = enderecoDto.getEstado();
        String cep = enderecoDto.getCep();

        return academiasProximasService.buscarAcademiasProximas(logradouro, bairro, cidade, estado, cep);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Endereco> adicionarEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.salvarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Integer id) {
        Optional<Endereco> enderecoOptional = enderecoService.buscarEnderecoPorId(id);
        return enderecoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Endereco>> buscarTodosEnderecos() {
        List<Endereco> enderecos = enderecoService.buscarTodosEnderecos();
        return ResponseEntity.ok(enderecos);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco novoEndereco) {
        Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, novoEndereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/api/academias/proximas")
    public ResponseEntity<List<AcademiaDto>> buscarAcademiasProximas(@RequestParam String logradouro, @RequestParam String bairro, @RequestParam String cidade, @RequestParam String estado, @RequestParam String cep) {
        return academiasProximasService.buscarAcademiasProximas(logradouro, bairro, cidade, estado,cep);
    }




}
