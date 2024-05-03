package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoCreateEditDto;
import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoMapper;
import API.nhyira.apivitalis.Service.RotinaTreinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotinaTreinos")
public class RotinaTreinoController {

    @Autowired
    private RotinaTreinoService rotinaTreinoService;

    @PostMapping
    public ResponseEntity<RotinaTreinoExibitionDto> create(@RequestBody @Valid RotinaTreinoCreateEditDto dto){
        RotinaTreinoExibitionDto rotina = rotinaTreinoService.create(dto);
        if (rotina == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(null).body(rotina);

    }

    @GetMapping
    public ResponseEntity<List<RotinaTreinoExibitionDto>> showAll(){
        List<RotinaTreinoExibitionDto> rotina = rotinaTreinoService.showAll();
        if (rotina.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rotina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RotinaTreinoExibitionDto> show(@PathVariable int id){
        RotinaTreinoExibitionDto rotina = rotinaTreinoService.show(id);
        if (rotina == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rotina);
    }


    @PutMapping("/{id}")
    public  ResponseEntity<RotinaTreinoExibitionDto> uptd(@PathVariable int id, @RequestBody @Valid RotinaTreinoCreateEditDto dto){
        RotinaTreinoExibitionDto rotina = rotinaTreinoService.updt(dto, id);
        if (rotina == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rotina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RotinaTreinoExibitionDto> delete(@PathVariable int id){
        boolean rotina = rotinaTreinoService.delete(id);
        if (!rotina){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


}
