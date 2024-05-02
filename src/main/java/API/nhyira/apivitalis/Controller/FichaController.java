package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Ficha.FichaCreateEditDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaExibitionDto;
import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Service.FichaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @PostMapping
    public ResponseEntity<FichaExibitionDto> create(@RequestBody @Valid FichaCreateEditDto createEditDto){
        FichaExibitionDto user = fichaService.create(createEditDto);
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(null).body(user);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<FichaExibitionDto> show(@PathVariable int id){
        FichaExibitionDto user = fichaService.showFicha(id);
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);

    }

    @PutMapping("/{id}")
    public  ResponseEntity<FichaExibitionDto> uptade(@PathVariable int id, @RequestBody @Valid FichaCreateEditDto dto){
        FichaExibitionDto user = fichaService.updtFicha(id, dto);
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        Boolean ficha = fichaService.delUser(id);
        if (ficha){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ordenadoPorDeficiencias")
    public ResponseEntity<List<FichaExibitionDto>> listarFichasOrdenadasPorDeficiencias() {
        List<FichaExibitionDto> fichasOrdenadas = fichaService.ordenarTodasFichasPorDeficiencias();
        if (fichasOrdenadas == null || fichasOrdenadas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fichasOrdenadas);
    }


}
