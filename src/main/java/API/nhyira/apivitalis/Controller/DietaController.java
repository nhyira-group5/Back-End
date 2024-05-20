package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Dieta.DietaExibitionDto;
import API.nhyira.apivitalis.DTO.Dieta.DietaMapper;
import API.nhyira.apivitalis.Entity.Dieta;
import API.nhyira.apivitalis.Service.DietaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/dietas")
@RestController
@RequiredArgsConstructor
public class DietaController {

    public final DietaService dietaService;

    @GetMapping("/{id}")
    public ResponseEntity<DietaExibitionDto> show(@PathVariable int id){
        Dieta dieta = dietaService.show(id);
        DietaExibitionDto exibitionDto = DietaMapper.toDto(dieta);
        return ResponseEntity.ok(exibitionDto);
    }

    @GetMapping
    public ResponseEntity<List<DietaExibitionDto>> showAll(){
        List<Dieta> dietaList = dietaService.showAll();
        List<DietaExibitionDto> exibitionDto = DietaMapper.toDto(dietaList);
        return ResponseEntity.ok(exibitionDto);
    }

}
