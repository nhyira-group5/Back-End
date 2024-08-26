package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Lembrete.LembreteCreateEditDto;
import API.nhyira.apivitalis.DTO.Lembrete.LembreteExibitionDto;
import API.nhyira.apivitalis.DTO.Lembrete.LembreteMapper;
import API.nhyira.apivitalis.Entity.Lembrete;
import API.nhyira.apivitalis.Service.LembreteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lembretes")
public class LembreteController {

    private final LembreteService lembreteService;

    @PostMapping
    public ResponseEntity<LembreteExibitionDto> create(@RequestBody @Valid LembreteCreateEditDto dto){
        Lembrete lembrete = LembreteMapper.toEntity(dto);
        Lembrete lembreteSalvo = lembreteService.create(lembrete, dto.getUsuarioId());
        LembreteExibitionDto exibitionDto = LembreteMapper.toDto(lembrete);
        URI uri = URI.create("/lembretes/" + exibitionDto.getId());
        return ResponseEntity.created(uri).body(exibitionDto);
    }


    @GetMapping("/{usuarioId}")
    public List<LembreteExibitionDto> getLembretesByUsuarioId(@PathVariable Integer usuarioId) {
        return lembreteService.findLembretesByUsuarioId(usuarioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean lembrete = lembreteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
