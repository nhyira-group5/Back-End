package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoCreateEditDto;
import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoMapper;
import API.nhyira.apivitalis.Entity.RotinaTreino;
import API.nhyira.apivitalis.Service.RotinaTreinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rotinaTreinos")
public class RotinaTreinoController {

    @Autowired
    private RotinaTreinoService rotinaTreinoService;

    @PostMapping
    public ResponseEntity<RotinaTreinoExibitionDto> create(@RequestBody @Valid RotinaTreinoCreateEditDto dto){
        RotinaTreino rotinaTreino = RotinaTreinoMapper.toEntity(dto);
        RotinaTreino rotina = rotinaTreinoService.create(rotinaTreino);
        URI uri = URI.create("/rotinaTreinos/"+ rotina);
        RotinaTreinoExibitionDto exibitionDto = RotinaTreinoMapper.toDto(rotina);

        return ResponseEntity.created(uri).body(exibitionDto);

    }

    @GetMapping
    public ResponseEntity<List<RotinaTreinoExibitionDto>> showAll(){
        List<RotinaTreino> rotina = rotinaTreinoService.showAll();
        List<RotinaTreinoExibitionDto> exibitionDtos = RotinaTreinoMapper.toDto(rotina);
        return ResponseEntity.ok(exibitionDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RotinaTreinoExibitionDto> show(@PathVariable int id){
        RotinaTreino rotina = rotinaTreinoService.show(id);
        RotinaTreinoExibitionDto exibitionDto = RotinaTreinoMapper.toDto(rotina);
        return ResponseEntity.ok(exibitionDto);
    }


    @PutMapping("/{id}")
    public  ResponseEntity<RotinaTreinoExibitionDto> uptd(@PathVariable int id, @RequestBody @Valid RotinaTreinoCreateEditDto dto){
        RotinaTreino rotina = rotinaTreinoService.updt(dto, id);
        RotinaTreinoExibitionDto exibitionDto = RotinaTreinoMapper.toDto(rotina);
        return ResponseEntity.ok(exibitionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RotinaTreinoExibitionDto> delete(@PathVariable int id){
        boolean rotina = rotinaTreinoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
