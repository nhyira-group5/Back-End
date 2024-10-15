package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.RotinaDiaria.RotinaDiariaExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaDiaria.RotinaDiariaMapper;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Service.RotinaDiariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rotinaDiarias")
public class RotinaDiariaController {
    private final RotinaDiariaService rotinaDiariaService;

    @GetMapping("/{id}")
    public ResponseEntity<RotinaDiariaExibitionDto> showPorIdDiario(@PathVariable int id) {
        if (id <= 0)
            throw new ErroClienteException("ID");
        RotinaDiaria rotinaDiaria = rotinaDiariaService.show(id);
        Integer totalExercicios = rotinaDiariaService.showQtdExercicios(rotinaDiaria);
        Integer totalExerciciosFeitos = rotinaDiariaService.showQtdExerciciosFeitos(rotinaDiaria);
        return ResponseEntity.ok().body(RotinaDiariaMapper.toDto(rotinaDiaria, totalExercicios, totalExerciciosFeitos));
    }

    @GetMapping("/diaria-atual/{idRotinaSemanal}")
    public ResponseEntity<RotinaDiariaExibitionDto> showCurrentDailyRoutine(@PathVariable int idRotinaSemanal) {
        if (idRotinaSemanal <= 0) throw new ErroClienteException("ID");
        RotinaDiaria rd = rotinaDiariaService.showCurrentDailyRoutine(idRotinaSemanal);
        Integer totalExercicios = rotinaDiariaService.showQtdExercicios(rd);
        Integer totalExerciciosFeitos = rotinaDiariaService.showQtdExerciciosFeitos(rd);
        return ResponseEntity.ok(RotinaDiariaMapper.toDto(rd, totalExercicios, totalExerciciosFeitos));
    }

    @GetMapping("/por-semana/{id}")
    public ResponseEntity<List<RotinaDiariaExibitionDto>> showPorIdSemanal(@PathVariable int id) {
        if (id <= 0)
            throw new ErroClienteException("ID");
        List<RotinaDiaria> rotinasDiarias = rotinaDiariaService.showPorSemanal(id);
        if (rotinasDiarias.isEmpty())
            return ResponseEntity.noContent().build();
        List<RotinaDiariaExibitionDto> dtosList = new ArrayList<>(0);
        for (RotinaDiaria rd : rotinasDiarias) {
            Integer totalExercicios = rotinaDiariaService.showQtdExercicios(rd);
            Integer totalExerciciosFeitos = rotinaDiariaService.showQtdExerciciosFeitos(rd);
            dtosList.add(RotinaDiariaMapper.toDto(rd, totalExercicios, totalExerciciosFeitos));
        }
        return ResponseEntity.ok(dtosList);
    }

    @GetMapping("/por-usuario/{id}")
    public ResponseEntity<RotinaDiariaExibitionDto> rotinaPorUsuario(@PathVariable int id) {
        RotinaDiaria rotinaDiaria = rotinaDiariaService.rotinaPorUsuario(id);
        Integer totalExercicios = rotinaDiariaService.showQtdExercicios(rotinaDiaria);
        Integer totalExerciciosFeitos = rotinaDiariaService.showQtdExerciciosFeitos(rotinaDiaria);
        return ResponseEntity.ok().body(RotinaDiariaMapper.toDto(rotinaDiaria, totalExercicios, totalExerciciosFeitos));
    }

    @PatchMapping("/concluir/{id}")
    public ResponseEntity<RotinaDiariaExibitionDto> concluirRotinaDiaria(
            @PathVariable int id,
            @RequestParam int concluido) {
        if (id <= 0)
            throw new ErroClienteException("ID");
        if (concluido < 0 || concluido > 1)
            throw new ErroClienteException("Concluido");
        RotinaDiaria rd = rotinaDiariaService.updateConcluido(id, concluido);
        Integer totalExercicios = rotinaDiariaService.showQtdExercicios(rd);
        Integer totalExerciciosFeitos = rotinaDiariaService.showQtdExerciciosFeitos(rd);
        return ResponseEntity.ok().body(RotinaDiariaMapper.toDto(rd, totalExercicios, totalExerciciosFeitos));
    }
}
