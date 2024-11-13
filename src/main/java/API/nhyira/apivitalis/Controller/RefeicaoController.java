package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoExibition;
import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoExibitionSemanalDto;
import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoMapper;
import API.nhyira.apivitalis.Entity.*;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Service.DietaService;
import API.nhyira.apivitalis.Service.MetaService;
import API.nhyira.apivitalis.Service.RefeicaoDiariaService;
import API.nhyira.apivitalis.Service.RefeicaoService;
import API.nhyira.apivitalis.Service.RotinaDiariaService;
import API.nhyira.apivitalis.Service.RotinaSemanalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/refeicoes")
@RequiredArgsConstructor
public class RefeicaoController {
    private final RefeicaoService refSrv;
    private final MetaService mSrv;
    private final DietaService dSrv;

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

    @GetMapping("/por-meta/{idMeta}")
    public ResponseEntity<List<RefeicaoExibition>> showAllByMeta(
        @PathVariable int idMeta
    ) {
        if (idMeta <= 0) throw new ErroClienteException("ID");
        Meta meta = mSrv.show(idMeta);
        List<Dieta> dietas = dSrv.showByMeta(meta);
        if (dietas.isEmpty()) return ResponseEntity.noContent().build();

        List<RefeicaoExibition> refeicoes = new ArrayList<>(0);
        for (Dieta d : dietas) {
            refeicoes.addAll(RefeicaoMapper.toDTO(refSrv.showRefeicaoByDieta(d)));
        }

        return refeicoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(refeicoes);
    }

    @GetMapping("/por-dieta/{idDieta}")
    public ResponseEntity<List<RefeicaoExibition>> showByDieta(
            @PathVariable int idDieta
    ) {
        if (idDieta <= 0) return ResponseEntity.badRequest().build();
        List<RefeicaoExibition> refeicoes = RefeicaoMapper.toDTO(refSrv.getAllRefeicoes());
        return refeicoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(refeicoes);
    }

    @GetMapping("/por-refeicao-diaria/{idRefeicaoDiaria}")
    public ResponseEntity<RefeicaoExibition> showByRefeicaoDiariaId(
            @PathVariable int idRefeicaoDiaria
    ) {
        if (idRefeicaoDiaria <= 0) throw new ErroClienteException("ID");
        RefeicaoExibition ref = RefeicaoMapper.toDTO(refSrv.showByRefeicaoDiaria(idRefeicaoDiaria));
        return ResponseEntity.ok(ref);
    }

    @GetMapping("/filtro/nome")
    public ResponseEntity<List<RefeicaoExibition>> showByNome(
            @RequestParam String nome
    ) {
        List<RefeicaoExibition> dtoList = RefeicaoMapper.toDTO(refSrv.showRefeicoesByNome(nome));
        return dtoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/por-meta/{idMeta}/filtro/nome")
    public ResponseEntity<List<RefeicaoExibition>> showByMetaAndName(
            @PathVariable int idMeta,
            @RequestParam String nome
    ) {
        List<RefeicaoExibition> dtoList = RefeicaoMapper.toDTO(refSrv.showMealsByMetaAndNome(idMeta, nome));
        return dtoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/por-semana/{idRotinaSemanal}")
    public ResponseEntity<List<RefeicaoExibitionSemanalDto>> buscarRefeiçõesPorSemana(
            @PathVariable int idRotinaSemanal
    ) {
        if (idRotinaSemanal <= 0) throw new ErroClienteException("ID Rotina Semanal");
        RotinaSemanal rs = rsSrv.show(idRotinaSemanal);
        List<RotinaDiaria> rotinasDiariasPelaSemana = rdSrv.showPorSemanal(rs.getIdRotinaSemanal());

        List<RefeicaoExibitionSemanalDto> dtoList = new ArrayList<>(0);
        for (RotinaDiaria rd : rotinasDiariasPelaSemana) {
            List<RefeicaoDiaria> refeicoesDiarias = refdSrv.showByRotinaDiaria(rd);
            for (RefeicaoDiaria refd : refeicoesDiarias) {
                Refeicao ref = refd.getRefeicaoId();
                List<Midia> midia = ref.getMidiaId();
                dtoList.add(RefeicaoMapper.toRefeicaoExibitionSemanalDto(ref, rd, refd, midia, refd.getIdRefeicaoDiaria()));
            }
        }
        return dtoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/por-dia/{idRotinaDiaria}")
    public ResponseEntity<List<RefeicaoExibition>> showByDailyRoutine(
            @PathVariable int idRotinaDiaria
    ) {
        if (idRotinaDiaria <= 0) throw new ErroClienteException("ID Rotina Diária");
        RotinaDiaria rd = rdSrv.show(idRotinaDiaria);
        List<Refeicao> refs = refSrv.showByRotinaDiaria(rd.getIdRotinaDiaria());
        return refs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(RefeicaoMapper.toDTO(refs));
    }
}
