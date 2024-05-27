package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Alimento.AlimentoExibitionDto;
import API.nhyira.apivitalis.DTO.Alimento.AlimentoMapper;
import API.nhyira.apivitalis.Service.AlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alimentos")
public class AlimentoController {
    private final AlimentoService aliSev;

    @GetMapping("/{id}")
    public ResponseEntity<AlimentoExibitionDto> showById(
            @PathVariable int id
    ) {
        if (id <= 0) return ResponseEntity.badRequest().build();
        aliSev.getAlimentoById(id);
        AlimentoExibitionDto alimento = AlimentoMapper.toDto(aliSev.getAlimentoById(id));
        return ResponseEntity.ok(alimento);
    }

    @GetMapping
    public ResponseEntity<List<AlimentoExibitionDto>> showAll() {
        List<AlimentoExibitionDto> alimentos = AlimentoMapper.toDto(aliSev.getAllAlimentos());
        return alimentos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(alimentos);
    }

    @GetMapping("/por-refeicao/{id}")
    public ResponseEntity<List<AlimentoExibitionDto>> showByRefeicao(
            @PathVariable int id
    ) {
        if (id <= 0) return ResponseEntity.badRequest().build();
        List<AlimentoExibitionDto> alimentos = AlimentoMapper.toDto(aliSev.getAlimentosByRefeicao(id));
        return alimentos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(alimentos);
    }
}
