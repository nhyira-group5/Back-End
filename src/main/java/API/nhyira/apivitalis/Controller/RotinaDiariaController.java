package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.RotinaDiaria.RotinaDiariaExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaDiaria.RotinaDiariaMapper;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Service.RotinaDiariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rotinaDiarias")
@RequiredArgsConstructor
public class RotinaDiariaController {

    private final RotinaDiariaService rotinaDiariaService;

    @GetMapping("/{id}")
    public ResponseEntity<List<RotinaDiariaExibitionDto>> show(@PathVariable int id){
        List<RotinaDiaria> rotinaDiaria = rotinaDiariaService.showPorSemanal(id);
        if (rotinaDiaria.isEmpty())return ResponseEntity.noContent().build();
        return ResponseEntity.ok(RotinaDiariaMapper.toDtos(rotinaDiaria));
    }

    @GetMapping("buscarIdDiario/{id}")
    public ResponseEntity<RotinaDiariaExibitionDto> showPorIdDiario(@PathVariable int id){
        RotinaDiaria rotinaDiaria = rotinaDiariaService.show(id);
        return ResponseEntity.ok(RotinaDiariaMapper.toDto(rotinaDiaria));
    }

}
