package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoExibition;
import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoExibitionSemanalDto;
import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoMapper;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.Entity.*;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Service.RefeicaoDiariaService;
import API.nhyira.apivitalis.Service.RefeicaoService;
import API.nhyira.apivitalis.Service.RotinaDiariaService;
import API.nhyira.apivitalis.Service.RotinaSemanalService;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/refeicoes")
@RequiredArgsConstructor
public class RefeicaoController {
    private final RefeicaoService refSrv;

    private final RotinaSemanalService rsSrv;
    private final RotinaDiariaService rdSrv;
    private final RefeicaoDiariaService refdSrv;

    @GetMapping("/{id}")
    public ResponseEntity<RefeicaoExibition> showById(
            @PathVariable int id
    ) {
        if (id <= 0) return ResponseEntity.badRequest().build();
        RefeicaoExibition refeicao = RefeicaoMapper.toDTO(refSrv.getRefeicaoById(id));
        return ResponseEntity.ok(refeicao);
    }

    @GetMapping
    public ResponseEntity<List<RefeicaoExibition>> showAll() {
        List<RefeicaoExibition> refeicoes = RefeicaoMapper.toDTO(refSrv.getAllRefeicoes());
        return refeicoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(refeicoes);
    }

    @GetMapping("/por-dieta/{id}")
    public ResponseEntity<List<RefeicaoExibition>> showByDieta(
            @PathVariable int id
    ) {
        if (id <= 0) return ResponseEntity.badRequest().build();
        List<RefeicaoExibition> refeicoes = RefeicaoMapper.toDTO(refSrv.getAllRefeicoes());
        return refeicoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(refeicoes);
    }

    @GetMapping("/filtro/nome")
    public ResponseEntity<List<RefeicaoExibition>> showByNome(
            @RequestParam String nome
    ) {
        List<RefeicaoExibition> dtoList = RefeicaoMapper.toDTO(refSrv.showRefeicoesByNome(nome));
        return dtoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/por-semana/{idRotinaSemanal}")
    public ResponseEntity<List<RefeicaoExibitionSemanalDto>> buscarRefeiçõesPorSemana(
            @PathVariable int idRotinaSemanal
    ) {
        if (idRotinaSemanal <= 0) throw new ErroClienteException("ID");
        RotinaSemanal rs = rsSrv.show(idRotinaSemanal);
        List<RotinaDiaria> rotinasDiariasPelaSemana = rdSrv.showPorSemanal(rs.getIdRotinaSemanal());

        List<RefeicaoExibitionSemanalDto> dtoList = new ArrayList<>(0);
        for (RotinaDiaria rd : rotinasDiariasPelaSemana) {
            List<RefeicaoDiaria> refeicoesDiarias = refdSrv.showByRotinaDiaria(rd);
            for (RefeicaoDiaria refd : refeicoesDiarias) {
                Refeicao ref = refd.getRefeicaoId();
                Midia midia = ref.getMidiaId();
                dtoList.add(RefeicaoMapper.toRefeicaoExibitionSemanalDto(ref, rd, refd, midia));
            }
        }
        return dtoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dtoList);
    }
}
