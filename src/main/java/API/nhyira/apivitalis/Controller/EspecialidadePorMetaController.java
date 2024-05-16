package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.EspecialidadePorMeta.EspecialidadePorMetaExibitionDto;
import API.nhyira.apivitalis.DTO.EspecialidadePorMeta.EspecialidadePorMetaMapper;
import API.nhyira.apivitalis.Entity.EspecialidadePorMeta;
import API.nhyira.apivitalis.Service.EspecialidadePorMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/especialidadesPorMetas")
@RequiredArgsConstructor
public class EspecialidadePorMetaController {

    private final EspecialidadePorMetaService service;

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadePorMetaExibitionDto> show(@PathVariable int id){
        EspecialidadePorMeta especialidadePorMeta = service.show(id);
        EspecialidadePorMetaExibitionDto exibitionDto = EspecialidadePorMetaMapper.toDto(especialidadePorMeta);
        return ResponseEntity.ok(exibitionDto);
    }


}
