package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.AlimentoPorRefeicao.AlimentoPorRefeicaoExibitionDto;
import API.nhyira.apivitalis.DTO.AlimentoPorRefeicao.AlimentoPorRefeicaoMapper;
import API.nhyira.apivitalis.Service.AlimentoPorRefeicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alimentos-por-refeicoes")
@RequiredArgsConstructor
public class AlimentoPorRefeicaoController {
    private final AlimentoPorRefeicaoService aprSrv;

    @GetMapping("/{id}")
    public ResponseEntity<AlimentoPorRefeicaoExibitionDto> showById(
            @PathVariable int id
    ) {
        if (id <= 0) return ResponseEntity.badRequest().build();
        AlimentoPorRefeicaoExibitionDto alimentoPorRefeicao = AlimentoPorRefeicaoMapper.toDto(aprSrv.getAlimentoPorRefeicaoById(id));
        return ResponseEntity.ok(alimentoPorRefeicao);
    }

    @GetMapping("/por-refeicao/{idRefeicao}")
    public ResponseEntity<List<AlimentoPorRefeicaoExibitionDto>> showByIdRefeicao(
            @PathVariable int idRefeicao
    ) {
        if (idRefeicao <= 0) return ResponseEntity.badRequest().build();
        List<AlimentoPorRefeicaoExibitionDto> alimentoPorRefeicao = AlimentoPorRefeicaoMapper.toDto(aprSrv.showAlimentoPorRefeicaoByIdRefeicao(idRefeicao));
        return ResponseEntity.ok(alimentoPorRefeicao);
    }

    @GetMapping("/por-alimento/{idAlimento}")
    public ResponseEntity<List<AlimentoPorRefeicaoExibitionDto>> showByIdAlimento(
            @PathVariable int idAlimento
    ) {
        if (idAlimento <= 0) return ResponseEntity.badRequest().build();
        List<AlimentoPorRefeicaoExibitionDto> alimentoPorRefeicao = AlimentoPorRefeicaoMapper.toDto(aprSrv.showAlimentoPorRefeicaoByIdAlimento(idAlimento));
        return ResponseEntity.ok(alimentoPorRefeicao);
    }

    @GetMapping
    public ResponseEntity<List<AlimentoPorRefeicaoExibitionDto>> showAll() {
        List<AlimentoPorRefeicaoExibitionDto> alimentosPorRefeicoes = AlimentoPorRefeicaoMapper.toDto(aprSrv.getAllAlimentoPorRefeicao());
        return alimentosPorRefeicoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(alimentosPorRefeicoes);
    }
}
