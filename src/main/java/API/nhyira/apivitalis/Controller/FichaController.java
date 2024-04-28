package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Ficha.FichaCreateEditDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaExibitionDto;
import API.nhyira.apivitalis.Service.FichaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public  ResponseEntity<FichaExibitionDto> show(@RequestParam int id){
        FichaExibitionDto user = fichaService.showFicha(id);
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);

    }

}
