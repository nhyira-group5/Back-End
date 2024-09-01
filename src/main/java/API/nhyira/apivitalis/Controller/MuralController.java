package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Mural.MuralCreateEditDto;
import API.nhyira.apivitalis.DTO.Mural.MuralExibitionDto;
import API.nhyira.apivitalis.DTO.Mural.MuralMapper;
import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Service.MidiaService;
import API.nhyira.apivitalis.Service.MuralService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/murais")
public class MuralController {
    private final MuralService muralService;

    @PostMapping
    public ResponseEntity<MuralExibitionDto> create(
            @RequestBody @Valid MuralCreateEditDto muralCreateEditDto
    ){
        Mural mural = MuralMapper.toEntity(muralCreateEditDto);
        Mural muralSalvo = muralService.create(mural, muralCreateEditDto.getUsuarioId(), muralCreateEditDto.getMidiaId());

        URI uri = URI.create("/murais/" + muralSalvo.getIdMural());
        return ResponseEntity.created(uri).body(MuralMapper.toDto(muralSalvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable int id
    ){
        if (id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        boolean mural = muralService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MuralExibitionDto> show(
            @PathVariable int id
    ) {
        Mural mural = muralService.show(id);
        return ResponseEntity.ok(MuralMapper.toDto(mural));
    }

    @GetMapping("/por-usuario/{id}")
    public ResponseEntity<List<MuralExibitionDto>> showPorUsuario(
            @PathVariable int id
    ) {
        List<Mural> murais = muralService.showPorUsuario(id);
        return ResponseEntity.ok(MuralMapper.toDtoList(murais));
    }

    @GetMapping("/por-data")
    public ResponseEntity<List<MuralExibitionDto>> showPorData(
            @RequestParam LocalDate date
    ) {
        List<Mural> mural = muralService.showPorData(date);
        return ResponseEntity.ok(MuralMapper.toDtoList(mural));
    }
    
    @GetMapping("/por-data/usuario/{idUsuario}")
    public ResponseEntity<List<MuralExibitionDto>> showPorData(
            @PathVariable int idUsuario,
            @RequestParam LocalDate date
    ) {
        List<Mural> murais = muralService.showPorDataUsuario(date, idUsuario);
        return ResponseEntity.ok(MuralMapper.toDtoList(murais));
    }
}
