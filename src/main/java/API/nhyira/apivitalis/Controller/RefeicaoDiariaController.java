package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.RefeicaoDiaria.RefeicaoDiariaExibitionDto;
import API.nhyira.apivitalis.DTO.RefeicaoDiaria.RefeicaoDiariaMapper;
import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Service.RefeicaoDiariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/refeicaoDiarias")
public class RefeicaoDiariaController {

    private final RefeicaoDiariaService diariaService;

    @GetMapping("/{id}")
    public ResponseEntity<RefeicaoDiariaExibitionDto> show(@PathVariable int id){
        RefeicaoDiaria refeicaoDiaria = diariaService.show(id);
        return ResponseEntity.ok(RefeicaoDiariaMapper.toDto(refeicaoDiaria));
    }

}
