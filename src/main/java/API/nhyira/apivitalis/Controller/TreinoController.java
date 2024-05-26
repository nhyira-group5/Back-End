package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Treino.TreinoExibitionDto;
import API.nhyira.apivitalis.DTO.Treino.TreinoMapper;
import API.nhyira.apivitalis.Entity.Treino;
import API.nhyira.apivitalis.Service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treinos")
@RequiredArgsConstructor
public class TreinoController {

    private final TreinoService treinoService;

    @GetMapping("/{id}")
    public ResponseEntity<TreinoExibitionDto> show(@PathVariable int id){
        Treino treino = treinoService.show(id);
        return ResponseEntity.ok(TreinoMapper.toDto(treino));
    }


}
