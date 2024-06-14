package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.EspecialidadePorPersonal.EspecialidadePorPersonalCreateEditDto;
import API.nhyira.apivitalis.DTO.EspecialidadePorPersonal.EspecialidadePorPersonalExibitonDto;
import API.nhyira.apivitalis.DTO.EspecialidadePorPersonal.EspecialidadePorPersonalMapper;
import API.nhyira.apivitalis.Entity.EspecialidadePorPersonal;
import API.nhyira.apivitalis.Service.EspecialidadePorPersonalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/especialidadesPersonais")
@RequiredArgsConstructor
public class EspecialidadePorPersonalController {
    private final EspecialidadePorPersonalService personalService;


    @PostMapping
    public ResponseEntity<EspecialidadePorPersonalExibitonDto> create(@RequestBody @Valid EspecialidadePorPersonalCreateEditDto dto){
        EspecialidadePorPersonal especialidade = EspecialidadePorPersonalMapper.toEntity(dto);
        EspecialidadePorPersonal novaEspecialidade = personalService.create(especialidade, dto.getPersonalId(), dto.getEspecialidadeId());
        EspecialidadePorPersonalExibitonDto exibitionDto = EspecialidadePorPersonalMapper.toDto(novaEspecialidade);
        URI uri = URI.create("/especialidadesPersonais/" + novaEspecialidade.getIdEspecialidadePersonal());

        return ResponseEntity.created(uri).body(exibitionDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EspecialidadePorPersonalExibitonDto>> show(@PathVariable int id){
        List<EspecialidadePorPersonal> especialidade = personalService.show(id);
        List<EspecialidadePorPersonalExibitonDto> exibitonDtos = EspecialidadePorPersonalMapper.toDto(especialidade);
        return ResponseEntity.ok(exibitonDtos);
    }
}
