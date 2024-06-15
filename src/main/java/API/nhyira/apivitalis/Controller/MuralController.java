package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Mural.MuralExibitionDto;
import API.nhyira.apivitalis.DTO.Mural.MuralMapper;
import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Service.MuralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/murais")
public class MuralController {
    private final MuralService muralService;

    @GetMapping("/{id}")
    public ResponseEntity<MuralExibitionDto> show(@PathVariable int id) {
        Mural mural = muralService.show(id);
        return ResponseEntity.ok(MuralMapper.toDto(mural));
    }

    @GetMapping("/por-usuario/{id}")
    public ResponseEntity<List<MuralExibitionDto>> showPorUsuario(@PathVariable int id) {
        List<Mural> murais = muralService.showPorUsuario(id);
        return ResponseEntity.ok(MuralMapper.toDto(murais));
    }

    @GetMapping("/por-data")
    public ResponseEntity<List<MuralExibitionDto>> showPorData(
            @RequestParam LocalDate date) {
        List<Mural> mural = muralService.showPorData(date);
        return ResponseEntity.ok(MuralMapper.toDto(mural));
    }
    
    @GetMapping("/por-data/usuario/{idUsuario}")
    public ResponseEntity<List<MuralExibitionDto>> showPorData(
            @PathVariable int idUsuario, @RequestParam LocalDate date) {
        List<Mural> murais = muralService.showPorDataUsuario(date, idUsuario);
        return ResponseEntity.ok(MuralMapper.toDto(murais));
    }
}
