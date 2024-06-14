package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Ficha.FichaCreateEditDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaExibitionDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaMapper;
import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Service.FichaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @PostMapping
    public ResponseEntity<FichaExibitionDto> create(@RequestBody @Valid FichaCreateEditDto createEditDto){
        Ficha ficha = FichaMapper.toEntity(createEditDto);
        Ficha user = fichaService.create(ficha, createEditDto.getUsuarioId());
        URI uri = URI.create("/fichas/"+user.getIdFicha());
        FichaExibitionDto exibitionDto = FichaMapper.toDto(user);
        return ResponseEntity.created(uri).body(exibitionDto);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<FichaExibitionDto> show(@PathVariable int id){
        Ficha user = fichaService.showFicha(id);
        FichaExibitionDto exibitionDto = FichaMapper.toDto(user);
        return ResponseEntity.ok(exibitionDto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<FichaExibitionDto> uptade(@PathVariable int id, @RequestBody @Valid FichaCreateEditDto dto){
        Ficha user = fichaService.updtFicha(id, dto);
        FichaExibitionDto exibitionDto = FichaMapper.toDto(user);
        return ResponseEntity.ok(exibitionDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        Boolean ficha = fichaService.delUser(id);
        if (ficha){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping("/ordenadoPorDeficiencias")
//    public ResponseEntity<List<FichaExibitionDto>> listarFichasOrdenadasPorDeficiencias() {
//        List<FichaExibitionDto> fichasOrdenadas = fichaService.ordenarTodasFichasPorDeficiencias();
//        if (fichasOrdenadas == null || fichasOrdenadas.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(fichasOrdenadas);
//    }

}
