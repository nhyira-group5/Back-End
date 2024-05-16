package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Especialidade.EspecialidadeExibitionDto;
import API.nhyira.apivitalis.DTO.Especialidade.EspecialidadeMapper;
import API.nhyira.apivitalis.Entity.Especialidade;
import API.nhyira.apivitalis.Service.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
@RequiredArgsConstructor
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    @GetMapping
    public ResponseEntity<List<EspecialidadeExibitionDto>> showAll(){
        List<Especialidade> especialidades = especialidadeService.showall();
        List<EspecialidadeExibitionDto> exibitionDtos = EspecialidadeMapper.toDto(especialidades);
        return ResponseEntity.ok(exibitionDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeExibitionDto> show(@PathVariable int id){
        Especialidade especialidade = especialidadeService.show(id);
        EspecialidadeExibitionDto exibitionDto = EspecialidadeMapper.toDto(especialidade);
        return ResponseEntity.ok(exibitionDto);
    }
}
