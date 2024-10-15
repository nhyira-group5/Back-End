package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Treino.TreinoExibitionDto;
import API.nhyira.apivitalis.DTO.Treino.TreinoExibitionSemanalDto;
import API.nhyira.apivitalis.DTO.Treino.TreinoMapper;
import API.nhyira.apivitalis.DTO.Treino.TreinoRelatorioDto;
import API.nhyira.apivitalis.Entity.*;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Service.RotinaDiariaService;
import API.nhyira.apivitalis.Service.RotinaSemanalService;
import API.nhyira.apivitalis.Service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/treinos")
public class TreinoController {
    private final TreinoService treinoService;
    private final RotinaSemanalService rsSrv;
    private final RotinaDiariaService rdSrv;

    @GetMapping("/{idRotinaDiaria}")
    public ResponseEntity<List<TreinoExibitionDto>> show(@PathVariable int idRotinaDiaria) {
        if (idRotinaDiaria <= 0) throw new ErroClienteException("ID");
        List<Treino> treino = treinoService.showPorDiaria(idRotinaDiaria);
        if (treino.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(TreinoMapper.toDto(treino));
    }

    @GetMapping("/relatorio/{id}")
    public ResponseEntity<List<TreinoRelatorioDto>> showT(@PathVariable int id) {
        if (id <= 0)
            throw new ErroClienteException("ID");
        List<Integer> count = treinoService.showCount(id);
        List<String> nome = treinoService.showString(id);
        return ResponseEntity.ok(TreinoMapper.toDtoRelatorio(count, nome));
    }

    @GetMapping("buscarIdTreinos/{id}")
    public ResponseEntity<TreinoExibitionDto> buscarPorIdTreinos(@PathVariable int id) {
        if (id <= 0)
            throw new ErroClienteException("ID");
        Treino treino = treinoService.show(id);
        return ResponseEntity.ok(TreinoMapper.toDto(treino));
    }

    @GetMapping("/por-semana/{idRotinaSemanal}")
    public ResponseEntity<List<TreinoExibitionSemanalDto>> buscarTreinosPorSemana(
            @PathVariable int idRotinaSemanal) {
        if (idRotinaSemanal <= 0)
            throw new ErroClienteException("ID");
        RotinaSemanal rs = rsSrv.show(idRotinaSemanal);
        List<RotinaDiaria> rotinasDiariasPelaSemana = rdSrv.showPorSemanal(rs.getIdRotinaSemanal());

        List<TreinoExibitionSemanalDto> dtoList = new ArrayList<>(0);
        for (RotinaDiaria rd : rotinasDiariasPelaSemana) {
            List<Treino> treinosDiarios = treinoService.showByRotinaDiaria(rd);
            for (Treino td : treinosDiarios) {
                Exercicio ex = td.getExercicioId();
//                Midia midia = ex.getMidia();
                dtoList.add(TreinoMapper.toTreinoExibitionSemanalDto(ex, td, rd));
            }
        }

        return dtoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/por-dia/{idRotinaDiaria}")
    public ResponseEntity<List<TreinoExibitionSemanalDto>> buscarTreinosPorDia(
            @PathVariable int idRotinaDiaria) {
        if (idRotinaDiaria <= 0)
            throw new ErroClienteException("ID");
        RotinaDiaria rd = rdSrv.show(idRotinaDiaria);
        List<TreinoExibitionSemanalDto> dtoList = new ArrayList<>(0);
        List<Treino> treinosDiarios = treinoService.showByRotinaDiaria(rd);
        for (Treino td : treinosDiarios) {
            Exercicio ex = td.getExercicioId();
//            Midia midia = ex.getMidia();
            dtoList.add(TreinoMapper.toTreinoExibitionSemanalDto(ex, td, rd));
        }

        return dtoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dtoList);
    }

    @PatchMapping("/concluir/{id}")
    public ResponseEntity<TreinoExibitionDto> concluirRotinaMensal(
            @PathVariable int id,
            @RequestParam int concluido) {
        if (id <= 0)
            throw new ErroClienteException("ID");
        if (concluido < 0 || concluido > 1)
            throw new ErroClienteException("Concluido");
        TreinoExibitionDto t = TreinoMapper.toDto(treinoService.updateConcluido(id, concluido));
        return ResponseEntity.ok().body(t);
    }
}
