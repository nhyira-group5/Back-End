package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.RefeicaoPorDieta.RefeicaoPorDietaExibitionDto;
import API.nhyira.apivitalis.DTO.RefeicaoPorDieta.RefeicaoPorDietaMapper;
import API.nhyira.apivitalis.Service.RefeicaoPorDietaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/refeicoes-por-dietas")
public class RefeicaoPorDietaController {
    private final RefeicaoPorDietaService rpdSrv;

    @GetMapping("/{id}")
    public ResponseEntity<RefeicaoPorDietaExibitionDto> showById(
            @PathVariable int id
    ) {
        if (id <= 0) return ResponseEntity.badRequest().build();
        RefeicaoPorDietaExibitionDto refeicaoPorDieta = RefeicaoPorDietaMapper.toDto(rpdSrv.getRefeicaoPorDietaById(id));
        return ResponseEntity.ok(refeicaoPorDieta);
    }

    @GetMapping
    public ResponseEntity<List<RefeicaoPorDietaExibitionDto>> showAll() {
        List<RefeicaoPorDietaExibitionDto> refeicoesPorDietas = RefeicaoPorDietaMapper.toDto(rpdSrv.getAllRefeicaoPorDieta());
        return refeicoesPorDietas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(refeicoesPorDietas);
    }
}
