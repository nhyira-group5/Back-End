package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalMapper;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Service.RotinaSemanalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rotinaSemanais")
public class RotinaSemanalController {


    private final RotinaSemanalService semanalService;

    @GetMapping("/{id}")
    public ResponseEntity<List<RotinaSemanalExibitionDto>> show(@PathVariable int id){
        List<RotinaSemanal> rotinaSemanal = semanalService.showAll(id);
        return ResponseEntity.ok(RotinaSemanalMapper.toDto(rotinaSemanal));
    }
}
