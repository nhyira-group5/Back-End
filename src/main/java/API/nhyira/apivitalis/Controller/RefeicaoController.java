package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Alimento.AlimentoExibitionDto;
import API.nhyira.apivitalis.DTO.Alimento.AlimentoMapper;
import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoExibitionDto;
import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoMapper;
import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Service.RefeicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/refeicoes")
@RequiredArgsConstructor
public class RefeicaoController {
    private final RefeicaoService refSrv;

    @GetMapping("/{id}")
    public ResponseEntity<RefeicaoExibitionDto> showById(
            @PathVariable int id
    ) {
        if (id <= 0) return ResponseEntity.badRequest().build();
        RefeicaoExibitionDto refeicao = RefeicaoMapper.toDto(refSrv.getRefeicaoById(id));
        return ResponseEntity.ok(refeicao);
    }

    @GetMapping
    public ResponseEntity<List<RefeicaoExibitionDto>> showAll() {
        List<RefeicaoExibitionDto> refeicoes = RefeicaoMapper.toDto(refSrv.getAllRefeicoes());
        return refeicoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(refeicoes);
    }

    @GetMapping("/por-dieta/{id}")
    public ResponseEntity<List<RefeicaoExibitionDto>> showByDieta(
            @PathVariable int id
    ) {
        if (id <= 0) return ResponseEntity.badRequest().build();
        List<RefeicaoExibitionDto> refeicoes = RefeicaoMapper.toDto(refSrv.getAllRefeicoes());
        return refeicoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(refeicoes);
    }
}
