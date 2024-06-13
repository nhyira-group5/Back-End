package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.RotinaDiaria.RotinaDiariaExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaDiaria.RotinaDiariaMapper;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Service.RotinaDiariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotinaDiarias")
@RequiredArgsConstructor
public class RotinaDiariaController {
    private final RotinaDiariaService rotinaDiariaService;

    @GetMapping("/{id}")
    public ResponseEntity<List<RotinaDiariaExibitionDto>> show(@PathVariable int id){
        if (id <= 0) throw new ErroClienteException("ID");
        List<RotinaDiaria> rotinaDiaria = rotinaDiariaService.showPorSemanal(id);
        if (rotinaDiaria.isEmpty())return ResponseEntity.noContent().build();
        return ResponseEntity.ok(RotinaDiariaMapper.toDtos(rotinaDiaria));
    }

    @GetMapping("buscarIdDiario/{id}")
    public ResponseEntity<RotinaDiariaExibitionDto> showPorIdDiario(@PathVariable int id){
        if (id <= 0) throw new ErroClienteException("ID");
        RotinaDiaria rotinaDiaria = rotinaDiariaService.show(id);
        return ResponseEntity.ok(RotinaDiariaMapper.toDto(rotinaDiaria));
    }

    @PatchMapping("/concluir/{id}")
    public ResponseEntity<RotinaDiariaExibitionDto> concluirRotinaDiaria(
            @PathVariable int id,
            @RequestParam int concluido
    ) {
        if (id <= 0) throw new ErroClienteException("ID");
        if (concluido < 0 || concluido > 1) throw new ErroClienteException("Concluido");
        RotinaDiariaExibitionDto rd = RotinaDiariaMapper.toDto(rotinaDiariaService.updateConcluido(id, concluido));
        return ResponseEntity.ok().body(rd);
    }
}
