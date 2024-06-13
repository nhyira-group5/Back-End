package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.RefeicaoDiaria.RefeicaoDiariaExibitionDto;
import API.nhyira.apivitalis.DTO.RefeicaoDiaria.RefeicaoDiariaMapper;
import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Service.RefeicaoDiariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/refeicaoDiarias")
public class RefeicaoDiariaController {
    private final RefeicaoDiariaService diariaService;

    @GetMapping("/{id}")
    public ResponseEntity<RefeicaoDiariaExibitionDto> show(@PathVariable int id){
        if (id <= 0) throw new ErroClienteException("ID");
        RefeicaoDiaria refeicaoDiaria = diariaService.show(id);
        return ResponseEntity.ok(RefeicaoDiariaMapper.toDto(refeicaoDiaria));
    }

    @PatchMapping("/concluir/{id}")
    public ResponseEntity<RefeicaoDiariaExibitionDto> concluirRotinaMensal(
            @PathVariable int id,
            @RequestParam int concluido
    ) {
        if (id <= 0) throw new ErroClienteException("ID");
        if (concluido < 0 || concluido > 1) throw new ErroClienteException("Concluido");
        RefeicaoDiariaExibitionDto refd = RefeicaoDiariaMapper.toDto(diariaService.updateConcluido(id, concluido));
        return ResponseEntity.ok().body(refd);
    }
}
