package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Treino.TreinoExibitionDto;
import API.nhyira.apivitalis.DTO.Treino.TreinoExibitionSemanalDto;
import API.nhyira.apivitalis.DTO.Treino.TreinoMapper;
import API.nhyira.apivitalis.Entity.*;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Service.RotinaDiariaService;
import API.nhyira.apivitalis.Service.RotinaSemanalService;
import API.nhyira.apivitalis.Service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/treinos")
@RequiredArgsConstructor
public class TreinoController {
    private final TreinoService treinoService;

    private final RotinaSemanalService rsSrv;
    private final RotinaDiariaService rdSrv;

    @GetMapping("/{id}")
    public ResponseEntity<List<TreinoExibitionDto>> show(@PathVariable int id){
        List<Treino> treino = treinoService.show(id);
        if (treino.isEmpty())return ResponseEntity.noContent().build();
        return ResponseEntity.ok(TreinoMapper.toDto(treino));
    }

    @GetMapping("buscarIdTreinos/{id}")
    public ResponseEntity<List<TreinoExibitionDto>> buscarPorIdTreinos(@PathVariable int id){
        List<Treino> treino = treinoService.show(id);
        return ResponseEntity.ok(TreinoMapper.toDto(treino));
    }

    @GetMapping("/por-semana/{idRotinaSemanal}")
    public ResponseEntity<List<TreinoExibitionSemanalDto>> buscarRefeiçõesPorSemana(
            @PathVariable int idRotinaSemanal
    ) {
        if (idRotinaSemanal <= 0) throw new ErroClienteException("ID");
        RotinaSemanal rs = rsSrv.show(idRotinaSemanal);
        List<RotinaDiaria> rotinasDiariasPelaSemana = rdSrv.showPorSemanal(rs.getIdRotinaSemanal());

        List<TreinoExibitionSemanalDto> dtoList = new ArrayList<>(0);
        for (RotinaDiaria rd : rotinasDiariasPelaSemana) {
            List<Treino> treinosDiarios = treinoService.showByRotinaDiaria(rd);
            for (Treino td : treinosDiarios) {
                Exercicio ex = td.getExercicioId();
                Midia midia = ex.getMidia();
                dtoList.add(TreinoMapper.toTreinoExibitionSemanalDto(ex, td, rd, midia));
            }
        }

        return dtoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dtoList);
    }
}
