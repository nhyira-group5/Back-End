package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Contrato.ContratoCreateEditDto;
import API.nhyira.apivitalis.DTO.Contrato.ContratoEditDto;
import API.nhyira.apivitalis.DTO.Contrato.ContratoExibitionDto;
import API.nhyira.apivitalis.DTO.Contrato.ContratoMapper;
import API.nhyira.apivitalis.Entity.Contrato;
import API.nhyira.apivitalis.Service.ContratoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    @PostMapping
    public ResponseEntity<ContratoExibitionDto> create(@RequestBody @Valid ContratoCreateEditDto contratoCreateEditDto){
        Contrato contrato = ContratoMapper.toEntity(contratoCreateEditDto);
        Contrato contratoSalvo = contratoService.create(contrato, contratoCreateEditDto.getUsuarioId(), contratoCreateEditDto.getPersonalId());
        URI uri = URI.create("/contratos/" + contratoSalvo.getIdContrato());
        return ResponseEntity.created(uri).body(ContratoMapper.toDto(contratoSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoExibitionDto> show(@PathVariable int id){
        Contrato contrato = contratoService.show(id);
        return ResponseEntity.ok(ContratoMapper.toDto(contrato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoExibitionDto> updt(@PathVariable int id, @RequestBody @Valid ContratoEditDto contratoEditDto){
        Contrato contrato = contratoService.updtUser(id, contratoEditDto);
        return ResponseEntity.ok(ContratoMapper.toDto(contrato));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean contrato = contratoService.del(id);
        return ResponseEntity.noContent().build();
    }


}
