package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Mural.MuralExibitionDto;
import API.nhyira.apivitalis.DTO.Mural.MuralMapper;
import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Service.MuralService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/murais")
public class MuralController  {

    private final MuralService muralService;


    @GetMapping("/{id}")
    public ResponseEntity<MuralExibitionDto> show(@PathVariable int id){
        Mural mural = muralService.show(id);
        return ResponseEntity.ok(MuralMapper.toDto(mural));
    }

    @GetMapping("/PorUsuarios/{id}")
    public ResponseEntity<MuralExibitionDto> showPorUsuario(@PathVariable int id){
        Mural mural = muralService.showPorUsuario(id);
        return ResponseEntity.ok(MuralMapper.toDto(mural));
    }

}
