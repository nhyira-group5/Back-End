package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalMapper;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Repository.RotinaMensalRepository;
import API.nhyira.apivitalis.Service.RotinaMensalService;
import API.nhyira.apivitalis.Service.RotinaSemanalService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rotinaSemanais")
@RequiredArgsConstructor
public class RotinaSemanalController {
    private final RotinaSemanalService semanalService;

    @GetMapping("/{id}")
    public ResponseEntity<RotinaSemanalExibitionDto> show(@PathVariable int id){
        RotinaSemanal rotinaSemanal = semanalService.show(id);
        return ResponseEntity.ok(RotinaSemanalMapper.toDto(rotinaSemanal));
    }

}
