package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Contrato.ContratoCreateEditDto;
import API.nhyira.apivitalis.DTO.Contrato.ContratoEditDto;
import API.nhyira.apivitalis.DTO.Contrato.ContratoExibitionDto;
import API.nhyira.apivitalis.DTO.Contrato.ContratoMapper;
import API.nhyira.apivitalis.Entity.Contrato;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Service.ContratoService;
import API.nhyira.apivitalis.Service.MetaService;
import API.nhyira.apivitalis.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contratos")
public class ContratoController {
    private final ContratoService contratoService;
    private final UsuarioService usuarioService;

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

    @GetMapping("/por-personal/{idPersonal}")
    public ResponseEntity<List<ContratoExibitionDto>> showPorPersonal(@PathVariable int idPersonal){
        List<ContratoExibitionDto> dto = new ArrayList<>(0);
        List<Contrato> contrato = contratoService.shows(idPersonal);
        for (Contrato c: contrato){
            Meta meta = usuarioService.searchMetaUsuario(c.getUsuarioId());
            dto.add(ContratoMapper.toDto(c, meta));
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoExibitionDto> updt(@PathVariable int id, @RequestBody @Valid ContratoEditDto contratoEditDto){
        Contrato contrato = contratoService.updtUser(id, contratoEditDto);
        Meta meta = usuarioService.searchMetaUsuario(contrato.getUsuarioId());
        return ResponseEntity.ok(ContratoMapper.toDto(contrato, meta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean contrato = contratoService.del(id);
        return ResponseEntity.noContent().build();
    }
}
