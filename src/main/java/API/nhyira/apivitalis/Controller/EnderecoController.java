package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Endereco.AcademiaDto;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoCreateEditDto;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoProximosGoogleDto;
import API.nhyira.apivitalis.Entity.EnderecoModel;
import API.nhyira.apivitalis.GooglePlaces.Service.AcademiasProximasService;
import API.nhyira.apivitalis.Service.EnderecoService;
import API.nhyira.apivitalis.utils.ListaUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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





    @GetMapping("/academias/{nome}")
    public ResponseEntity<List<AcademiaDto>> buscarAcademiasPorNome(@PathVariable String nome) {
       List<AcademiaDto> academias = academiasProximasService.buscarAcademiaPorNome(nome);
        return ResponseEntity.ok(academias);
    }




}
